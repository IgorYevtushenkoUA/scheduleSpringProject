package com.example.faculty.controller;

import com.example.faculty.database.dto.event.EventCreateDto;
import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.request.RequestCreateDto;
import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.services.interfaces.*;
import com.example.faculty.util.annotations.LogInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/user")
@CacheConfig(cacheNames = {"events"})
@LogInfo
public class EventController {

    private final IUserService userService;
    private final IEventService eventService;
    private final ISubjectService subjectService;
    private final IRequestService requestService;
    private final IAttendeeService attendeeService;

    public EventController(IUserService userService, IEventService eventService, ISubjectService subjectService, IRequestService requestService, IAttendeeService attendeeService) {
        this.userService = userService;
        this.eventService = eventService;
        this.subjectService = subjectService;
        this.requestService = requestService;
        this.attendeeService = attendeeService;
    }

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @GetMapping("/subjects/{id}/events/create")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('TEACHER')")
    public String createEventForSubject(Model model, @PathVariable("id") UUID id) {
        User user = getUser();
        List<UserResponseDto> teachers = user.getRole().equals(UserRole.TEACHER)
                ? userService.getAllTeacher().stream().filter(x -> x.getId().toString().equals(user.getId().toString())).collect(Collectors.toList())
                : userService.getAllTeacher();
        model.addAttribute("teachers", teachers);
        model.addAttribute("subject", subjectService.get(id));
        model.addAttribute("role", user.getRole().name());
        model.addAttribute("user", user);
        return "createEventForSubject";
    }

    @PostMapping("/subjects/{id}/events/create")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('TEACHER')")
    public String createEventForSubjectPost(Model model,
                                            @PathVariable("id") UUID id,
                                            @RequestParam("teacherUUID") UUID teacherUUID,
                                            @RequestParam("name") String name,
                                            @RequestParam("group") String group,
                                            @RequestParam("auditory") String auditory,
                                            @RequestParam("date") List<String> date,
                                            @RequestParam(value = "action", required = false) String action) {
        if (action.equals("create")) {

            for (int i = 0; i < date.size(); i++) {
                EventCreateDto event = EventCreateDto.builder()
                        .subjectId(id)
                        .userId(teacherUUID)
                        .name(name)
                        .group(group)
                        .auditory(auditory)
                        .datetime(transformString2Timestamp(date.get(i)))
                        .build();
                eventService.create(event);
            }
        }

        return "redirect:/api/user/subjects/{id}";
    }

    @PostMapping("/events/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteEvent(@PathVariable("id") UUID id) {
//        requestService.deleteByEvent(id);
//        attendeeService.deleteByEvent(id);

        eventService.delete(id);
        return "redirect:/api/user/calendar";
    }

    @GetMapping("/events/edit/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('TEACHER')")
    public String editEventGet(Model model, @PathVariable("id") UUID id) {
        EventResponseDto event = eventService.get(id).orElse(null);
        model.addAttribute("teachers", userService.getAllTeacher());
        model.addAttribute("event", event);
        model.addAttribute("datetime", transformTimestamp2String(event.getDatetime()));
        User user = getUser();
        model.addAttribute("role", user.getRole().name());
        model.addAttribute("user", user);
        return "editEvent";
    }

    @PostMapping("/events/edit/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('TEACHER')")
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
        return "redirect:/api/user/calendar";
    }

    private String transformTimestamp2String(Timestamp date) {
        return date.toString().substring(0, 16).replace(" ", "T");
    }


    private Timestamp transformString2Timestamp(String date) {
        return Timestamp.valueOf(date.replace("T", " ") + ":00.0");
    }
}
