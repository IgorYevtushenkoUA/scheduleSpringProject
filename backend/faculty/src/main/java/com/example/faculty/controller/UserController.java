package com.example.faculty.controller;

import com.example.faculty.database.dto.calendar.CalendarEventDto;
import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventInfoDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.database.dto.request.RequestCreateDto;
import com.example.faculty.database.dto.request.RequestResponseDto;
import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.dto.user.UserCreateDto;
import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.dto.user.UserUpdateDto;
import com.example.faculty.database.entity.Attendee;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.*;
import com.example.faculty.services.interfaces.*;
import com.example.faculty.util.annotations.LogInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
@LogInfo
@CacheConfig(cacheNames = {"users"})
public class UserController {
    private final IUserService userService;
    private final IEventService eventService;
    private final ISubjectService subjectService;
    private final IRequestService requestService;
    private final IAttendeeService attendeeService;

    private static List<String> specialities = new ArrayList<>();
    private static List<Integer> courses = new ArrayList<>();

    @Autowired
    private ResourceLoader resourceLoader;

    public UserController(IUserService userService, IEventService eventService, ISubjectService subjectService, IRequestService requestService, IAttendeeService attendeeService) {
        this.userService = userService;
        this.eventService = eventService;
        this.subjectService = subjectService;
        this.requestService = requestService;
        this.attendeeService = attendeeService;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER') or hasAuthority('ADMINISTRATOR')")
    public String getPersonalPage(Model model) {
        User user = getUser();
        model.addAttribute("user", userService.get(user.getId()));
        model.addAttribute("role", user.getRole().name());
        return "personalPage";
    }

    @GetMapping("/calendar")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER') or hasAuthority('ADMINISTRATOR')")
    public String showCalendar(Model model,
                               @RequestParam(value = "year", required = false) Integer year,
                               @RequestParam(value = "month", required = false) Integer month,
                               @RequestParam(value = "arrow", required = false) String arrow,
                               @RequestParam(value = "speciality", required = false) List<String> speciality,
                               @RequestParam(value = "course", required = false) List<Integer> course,
                               @RequestParam(value = "action", required = false) String action
    ) {

        specialities = setSpeciality(speciality, action);
        courses = setCourses(course, action);
        LocalDate localDate = getLocalDate(year, month, 1, arrow);
        List<Integer> days = getCurrentDays(localDate.getYear(), localDate.getMonth().getValue(), 1);
        List<Integer> weeks = new ArrayList<>();
        var weekDays = List.of(0, 1, 2, 3, 4, 5, 6);
        for (int i = 0; i < days.size() / 7; i++) {
            weeks.add(i);
        }

        User user = getUser();
        CalendarEventDto eventDto = user.getRole().equals(UserRole.ADMINISTRATOR)
                ? fillAdminShowCalendarDto(user.getId(), localDate, days, specialities, courses)
                : fillUserShowCalendarDto(user.getId(), localDate, days, user.getRole().name());

        model.addAttribute("weeks", weeks);
        model.addAttribute("weekDays", weekDays);
        model.addAttribute("localDate", localDate);
        model.addAttribute("days", days);
        model.addAttribute("eventDto", eventDto);
        model.addAttribute("specialities", Arrays.asList(Speciality.values()));
        model.addAttribute("courses", Arrays.asList(Courses.values()));
        model.addAttribute("specialityList", specialities);
        model.addAttribute("courseList", courses);
        model.addAttribute("role", user.getRole().name());
        return user.getRole().equals(UserRole.ADMINISTRATOR)
                ? "adminCalendar"
                : "calendar";
    }

    private static List<String> setSpeciality(List<String> speciality, String action) {
        if (speciality == null && action != null) {
            specialities = new ArrayList<>();
        } else if (speciality != null) {
            specialities = speciality;
        }
        return specialities;
    }

    private static List<Integer> setCourses(List<Integer> course, String action) {
        if (course == null && action != null) {
            courses = new ArrayList<>();
        } else if (course != null) {
            courses = course;
        }
        return courses;
    }


    private CalendarEventDto fillUserShowCalendarDto(UUID userUUID, LocalDate localDate, List<Integer> days, String role) {

        Map<Integer, List<EventInfoDto>> map = new HashMap<>();
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i) != null) {
                List<Event> events =
                        role.equals(UserRole.STUDENT.name())
                                ? eventService.findEventForUserByYearAndMonthAndDay(userUUID, localDate.getYear(), localDate.getMonth().getValue(), days.get(i))
                                : eventService.findEventForTeacherByYearAndMonthAndDay(userUUID, localDate.getYear(), localDate.getMonth().getValue(), days.get(i));
                List<EventInfoDto> eventShortInfoDtoList = buildEventInfoDtoList(events);
                map.put(days.get(i), eventShortInfoDtoList);
            }
        }
        CalendarEventDto calendarEventDto = CalendarEventDto.builder()
                .daysAtCalendar(map)
                .build();
        return calendarEventDto;
    }


    private CalendarEventDto fillAdminShowCalendarDto(UUID userUUID,
                                                      LocalDate localDate,
                                                      List<Integer> days,
                                                      List<String> specialities,
                                                      List<Integer> courses) {
        Map<Integer, List<EventInfoDto>> map = new HashMap<>();
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i) != null) {
                List<Event> events = new ArrayList<>();
                if (specialities.isEmpty() && courses.isEmpty()) {
                    events = eventService.findEventByYearAndMonthAndDay(localDate.getYear(),
                            localDate.getMonth().getValue(), days.get(i));
                } else if (!specialities.isEmpty() && courses.isEmpty()) {
                    events = eventService.findByYearAndMonthAndDayAndSpeciality(localDate.getYear(),
                            localDate.getMonth().getValue(), days.get(i), specialities);
                } else if (!courses.isEmpty() && specialities.isEmpty()) {
                    events = eventService.findByYearAndMonthAndDayAndCourse(localDate.getYear(),
                            localDate.getMonth().getValue(), days.get(i), courses);
                } else {
                    events = eventService.findByYearAndMonthAndDayAndSpecialityAndCourse(localDate.getYear(),
                            localDate.getMonth().getValue(), days.get(i), specialities, courses);
                }

                List<EventInfoDto> eventShortInfoDtoList = buildEventInfoDtoList(events);
                map.put(days.get(i), eventShortInfoDtoList);
            }
        }
        CalendarEventDto calendarEventDto = CalendarEventDto.builder()
                .daysAtCalendar(map)
                .build();
        return calendarEventDto;
    }

    private List<EventInfoDto> buildEventInfoDtoList(List<Event> events) {
        List<EventInfoDto> eventShortInfoDtoList = new ArrayList<>();
        for (Event e : events) {
            EventInfoDto eventShortInfoDto = EventInfoDto.builder()
                    .id(e.getId())
                    .group(e.getGroup())
                    .name(e.getName())
                    .auditory(e.getAuditory())
                    .subject(e.getSubject().getName())
                    .hours(e.getDatetime().getHours())
                    .minutes(e.getDatetime().getMinutes())
                    .build();
            eventShortInfoDtoList.add(eventShortInfoDto);
        }
        return eventShortInfoDtoList;
    }


    private LocalDate getLocalDate(Integer year, Integer month, Integer date, String arrow) {
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

    private List<Integer> getCurrentDays(int year, int month, int date) {
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
