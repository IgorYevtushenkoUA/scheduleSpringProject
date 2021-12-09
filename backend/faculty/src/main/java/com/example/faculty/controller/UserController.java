package com.example.faculty.controller;

import com.example.faculty.database.dto.calendar.CalendarEventDto;
import com.example.faculty.database.dto.event.EventShortInfoDto;
import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.user.UserCreateDto;
import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.dto.user.UserUpdateDto;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.enums.*;
import com.example.faculty.services.interfaces.IEventService;
import com.example.faculty.services.interfaces.ISubjectService;
import com.example.faculty.services.interfaces.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;
    private final IEventService eventService;
    private final ISubjectService subjectService;

    public UserController(IUserService userService, IEventService eventService, ISubjectService subjectService) {
        this.userService = userService;
        this.eventService = eventService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public String get(Model model, @PathVariable UUID id) {
        Optional<UserResponseDto> user = userService.get(id);

        model.addAttribute("user", user);
        return "personalPage";
    }

    @PostMapping("/create")
    public UserResponseDto create(@RequestBody UserCreateDto dto) {
        return userService.create(dto);
    }

    @PutMapping("/edit")
    public UserResponseDto update(@RequestBody UserUpdateDto dto) {
        return userService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

    // todo випрвити повідомлення про місяці, показує коректно , але в url - застаріла інформація
    @GetMapping("/calendar")
    public String showCalendar(Model model,
                               @RequestParam(value = "year", required = false) Integer year,
                               @RequestParam(value = "month", required = false) Integer month,
                               @RequestParam(value = "arrow", required = false) String arrow
    ) {

        LocalDate localDate = getLocalDate(year, month, 1, arrow);
        List<Integer> days = getCurrentDays(localDate.getYear(), localDate.getMonth().getValue(), 1);
//        --------------------------
        List<Integer> weeks = new ArrayList<>();
        var weekDays = List.of(0, 1, 2, 3, 4, 5, 6);
        for (int i = 0; i < days.size() / 7; i++) {
            weeks.add(i);
        }

        model.addAttribute("weeks", weeks);
        model.addAttribute("weekDays", weekDays);
/*
                --------------------------
         todo add userUUID
        */
        int tempUserId = 1;
        CalendarEventDto eventDto = fillUserShowCalendarDto(1, localDate, days);

        model.addAttribute("localDate", localDate);
        model.addAttribute("days", days);
        model.addAttribute("eventDto", eventDto);
        return "calendar";
    }

    @GetMapping("/subject/create")
    public String createSubjectGet(Model model) {
        model.addAttribute("faculties", Arrays.asList(Faculty.values()));
        model.addAttribute("specialities", Arrays.asList(Speciality.values()));
        model.addAttribute("courseB", Arrays.asList(CourseB.values()));
        model.addAttribute("courseM", Arrays.asList(CourseM.values()));
        model.addAttribute("trim", Arrays.asList(Trim.values()));

        return "createSubject";
    }

    @PostMapping("/subject/create")
    public String createSubjectPost(
            @RequestParam("name") String name,
            @RequestParam("faculty") String faculty,
            @RequestParam("speciality") String speciality,
            @RequestParam("course") Integer course,
            @RequestParam("code") String code,
            @RequestParam("trim") String trim,
            @RequestParam(value = "action", required = true) String action) {

        if (action.equals("create")) {
            SubjectCreateDto subject = SubjectCreateDto.builder()
                    .name(name)
                    .faculty(faculty)
                    .speciality(speciality)
                    .course(course)
                    .code(Integer.parseInt(code))
                    .trim(trim)
                    .build();
            subjectService.create(subject);
        }
        return "redirect:/subjects";
    }


    public CalendarEventDto fillUserShowCalendarDto(int userId, LocalDate localDate, List<Integer> days) {

        Map<Integer, List<EventShortInfoDto>> map = new HashMap<>();
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i) != null) {
                List<Event> events = eventService.findEventForUserByYearAndMonthAndDay(localDate.getYear(),
                        localDate.getMonth().getValue(), days.get(i));

                List<EventShortInfoDto> eventShortInfoDtoList = new ArrayList<>();
                for (Event e : events) {
                    EventShortInfoDto eventShortInfoDto = EventShortInfoDto.builder()
                            .group(e.getGroup())
                            .name(e.getName())
                            .auditory(e.getAuditory())
                            .subject(e.getSubject().getName())
                            .hours(e.getDatetime().getHours())
                            .minutes(e.getDatetime().getMinutes())
                            .build();
                    eventShortInfoDtoList.add(eventShortInfoDto);
                }

                map.put(days.get(i), eventShortInfoDtoList);
            }
        }
        CalendarEventDto calendarEventDto = CalendarEventDto.builder()
                .daysAtCalendar(map)
                .build();
        return calendarEventDto;
    }

    public LocalDate getLocalDate(Integer year, Integer month, Integer date, String arrow) {
        LocalDate localDate = LocalDate.now();
        if (month == null || year == null || date == null || arrow == null) {
            year = localDate.getYear();
            month = localDate.getMonth().getValue();
            date = 1;
        } else if (arrow.equals("next")) {
            if (month == 12) {
                year++;
                month = 1;
            } else {
                month++;
            }
        } else if (arrow.equals("previous")) {
            if (month == 1) {
                year--;
                month = 12;
            } else {
                month--;
            }
        }
        return LocalDate.of(year, month, date);
    }

    public List<Integer> getCurrentDays(int year, int month, int date) {
        List<Integer> days = new ArrayList<>();

        LocalDate initial = LocalDate.of(year, month, date);

        int dayOfMonth = initial.lengthOfMonth();
        int firstDayOfWeek = initial.getDayOfWeek().getValue();
        int weekOfMonth = (int) (Math.ceil((double) (dayOfMonth) / 7));
        int allDaysInMonthCalendar = 7 * weekOfMonth;
        while (firstDayOfWeek > 1) {
            days.add(null);
            firstDayOfWeek--;
        }
        for (int i = 1; i <= dayOfMonth; i++) {
            days.add(i);
        }
        for (int i = days.size(); i < allDaysInMonthCalendar; i++) {
            days.add(null);
        }
        return days;
    }

}
