package com.example.faculty.controller;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.entity.Attendee;
import com.example.faculty.database.entity.Event;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.*;
import com.example.faculty.services.interfaces.*;
import com.example.faculty.util.annotations.LogInfo;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
@CacheConfig(cacheNames = {"subjects"})
@LogInfo
public class SubjectController {

    private final IUserService userService;
    private final IEventService eventService;
    private final ISubjectService subjectService;
    private final IRequestService requestService;
    private final IAttendeeService attendeeService;

    public SubjectController(IUserService userService, IEventService eventService, ISubjectService subjectService, IRequestService requestService, IAttendeeService attendeeService) {
        this.userService = userService;
        this.eventService = eventService;
        this.subjectService = subjectService;
        this.requestService = requestService;
        this.attendeeService = attendeeService;
    }


    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @SneakyThrows
    @GetMapping("/subjects")
    @PreAuthorize("hasAuthority('STUDENT') or hasAuthority('TEACHER') or hasAuthority('ADMINISTRATOR')")
    public String showAllSubjects(Model model,
                                  @RequestParam(value = "name", defaultValue = "") String name) {

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
        model.addAttribute("courses", Arrays.asList(Courses.values()));
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

}
