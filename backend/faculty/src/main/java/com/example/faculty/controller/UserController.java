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

//    @ModelAttribute
//    public void addAttributes(Model model) {
////        String role = getUser().getRole().name();
//        model.addAttribute("role", "!Guest");
//    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public String get(Model model, @PathVariable UUID id) {
        model.addAttribute("user", userService.get(id));
        return "personalPage";
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER') or hasAuthority('ADMINISTRATOR')")
    public String getPersonalPage(Model model) {
        User user = getUser();
        model.addAttribute("user", userService.get(user.getId()));
        model.addAttribute("role", user.getRole().name());
        return "personalPage";
    }

    @PostMapping("/create")
    public UserResponseDto create(@RequestBody UserCreateDto dto) {
        return userService.create(dto);
    }

    @PutMapping("/edit")
    @CachePut(key = "#dto.id")
    public UserResponseDto update(@RequestBody UserUpdateDto dto) {
        return userService.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#dto.id")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
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

    @SneakyThrows
    @GetMapping("/subjects")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER') or hasAuthority('ADMINISTRATOR')")
    public String showAllSubjects(Model model,
                                  @RequestParam(value = "name", defaultValue = "") String name) {

        System.out.println("User is now " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<SubjectResponseDto> subjects = name.equals("")
                ? subjectService.getAll()
                : subjectService.getByName(name);

        model.addAttribute("subjects", subjects);
        model.addAttribute("role", getUser().getRole().name());
        return "subjects";
    }

    @GetMapping("/subjects/{id}")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER') or hasAuthority('ADMINISTRATOR')")
    public String subjectGet(Model model, @PathVariable("id") UUID id) {
        List<Event> events = eventService.findAllBySubject(id);
        User user = getUser();
        List<String> listOfEnrolledGroup = eventService.findAllStudentEventsBySubject(user.getId(), id)
                .stream()
                .map(Event::getGroup)
                .distinct()
                .collect(Collectors.toList());

        Map<String, Event> eventsMap = new TreeMap<>();
        for (Event e : events) {
            eventsMap.putIfAbsent(e.getGroup(), e);
        }

        model.addAttribute("subject", subjectService.get(id));
        model.addAttribute("eventsMap", eventsMap);
        model.addAttribute("listOfEnrolledGroup", listOfEnrolledGroup);
        model.addAttribute("role", user.getRole().name());

        return "subject";
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/subjects/{id}/{group}")
    public String enrollToSubject(@RequestParam("action") String action,
                                  @PathVariable("id") UUID id,
                                  @PathVariable("group") String group) {
        List<Event> events = eventService.findAllBySubjectAndGroup(id, group);
        User user = getUser();
        for (Event e : events) {
            if (action.equals("enroll")) {
                Attendee a = Attendee.builder()
                        .user(user)
                        .event(e)
                        .build();
                attendeeService.create(a);
            } else {
                Attendee a = attendeeService.getByUserAndEvent(user.getId(), e.getId());
                attendeeService.delete(a.getId());
            }
        }
        return "redirect:/api/user/subjects/{id}";
    }

    @GetMapping("/subjects/create")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String createSubjectGet(Model model) {
        model.addAttribute("faculties", Arrays.asList(Faculty.values()));
        model.addAttribute("specialities", Arrays.asList(Speciality.values()));
        model.addAttribute("courseB", Arrays.asList(CourseB.values()));
        model.addAttribute("courseM", Arrays.asList(CourseM.values()));
        model.addAttribute("trim", Arrays.asList(Trim.values()));
        model.addAttribute("role", getUser().getRole().name());
        return "createSubject";
    }

    @PostMapping("/subjects/create")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
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

        return "redirect:/api/user/subjects";
    }

    @GetMapping("/events/create")
    public String createEventGet(Model model) {
        model.addAttribute("teachers", userService.getAll());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("role", getUser().getRole().name());
        return "createEvent";
    }

    @PostMapping("/events/create")
    public String createEventPost(Model model,
                                  @RequestParam("subjectUUID") UUID subjectUUID,
                                  @RequestParam("teacherUUID") UUID teacherUUID,
                                  @RequestParam("name") String name,
                                  @RequestParam("group") String group,
                                  @RequestParam("auditory") String auditory,
                                  @RequestParam("date") List<String> date,
                                  @RequestParam(value = "action", required = false) String action) {
        if (action.equals("create")) {

            for (int i = 0; i < date.size(); i++) {
                EventCreateDto event = EventCreateDto.builder()
                        .subjectId(subjectUUID)
                        .userId(teacherUUID)
                        .name(name)
                        .group(group)
                        .auditory(auditory)
                        .datetime(transformString2Timestamp(date.get(i)))
                        .build();
                eventService.create(event);
            }
        }

        return "redirect:/api/user";
    }


    @GetMapping("/events/delete/{id}/{place}")
    public String deleteEvent1(Model model) {
        // todo write to delte events in all [attendee, events]
        model.addAttribute("role", getUser().getRole().name());
        return "redirect:/api/user/events/{id}";
    }

    @PostMapping("/events/delete/{id}/{place}")
    public String deleteEvent(@PathVariable("id") UUID id) {
        // todo write to delte events in all [attendee, events]
        return "redirect:/api/user/events/{id}";
    }

    @GetMapping("/events/edit/{id}")
    public String editEventGet(Model model, @PathVariable("id") UUID id) {
        EventResponseDto event = eventService.get(id).orElse(null);
        model.addAttribute("event", event);
        model.addAttribute("datetime", transformTimestamp2String(event.getDatetime()));
        return "editEvent";
    }

    @PostMapping("/events/edit/{id}")
    public String editEventPost(
            @PathVariable("id") UUID id,
            @RequestParam("group") String group,
            @RequestParam("auditory") String auditory,
            @RequestParam("date") String date,
            @RequestParam("action") String action) {

        if (action.equals("save")) {
            EventResponseDto event = eventService.get(id).orElse(null);
            RequestCreateDto request = RequestCreateDto.builder()
                    .time(new Timestamp(System.currentTimeMillis()))
                    .userId(event.getUser().getId())
                    .eventId(event.getId())
                    .name(event.getName())
                    .group(group)
                    .auditory(auditory)
                    .datetime(transformString2Timestamp(date))
                    .subjectId(event.getSubject().getId())
                    .build();

            requestService.create(request);
        }
        return "redirect:/api/user/requests";
    }

    @GetMapping("/requests")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String showAllRequests(Model model) {
        List<RequestResponseDto> requests = requestService.getAll();
        List<Optional<EventResponseDto>> oldEvents = new ArrayList<>();
        for (RequestResponseDto r : requests) {
            System.out.println(r.getEvent().getId());
            oldEvents.add(eventService.get(r.getEvent().getId()));
        }
        model.addAttribute("requests", requests);
        model.addAttribute("oldEvents", oldEvents);
        model.addAttribute("role", getUser().getRole().name());
        return "requestsList";
    }

    @PostMapping("/requests/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String requestPost(@PathVariable("id") UUID id,
                              @RequestParam("action") String action) {
        if (action.equals("confirm")) {
            RequestResponseDto request = requestService.get(id).orElse(null);
            EventUpdateDto event = EventUpdateDto.builder()
                    .id(request.getEvent().getId())
                    .datetime(request.getDatetime())
                    .subjectId(request.getSubject().getId())
                    .userId(request.getUser().getId())
                    .group(request.getGroup())
                    .name(request.getName())
                    .auditory(request.getAuditory())
                    .build();
            eventService.update(event);
        }
        requestService.delete(id);
        return "redirect:/api/user/requests";
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

    private String transformTimestamp2String(Timestamp date) {
        return date.toString().substring(0, 16).replace(" ", "T");
    }


    private Timestamp transformString2Timestamp(String date) {
        return Timestamp.valueOf(date.replace("T", " ") + ":00.0");
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
