package com.example.faculty.controller;

import com.example.faculty.database.dto.event.EventResponseDto;
import com.example.faculty.database.dto.event.EventUpdateDto;
import com.example.faculty.database.dto.request.RequestResponseDto;
import com.example.faculty.database.entity.User;
import com.example.faculty.services.interfaces.*;
import com.example.faculty.util.annotations.LogInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/user")
@LogInfo
@CacheConfig(cacheNames = {"requests"})
public class RequestController {

    private final IUserService userService;
    private final IEventService eventService;
    private final ISubjectService subjectService;
    private final IRequestService requestService;
    private final IAttendeeService attendeeService;

    public RequestController(IUserService userService, IEventService eventService, ISubjectService subjectService, IRequestService requestService, IAttendeeService attendeeService) {
        this.userService = userService;
        this.eventService = eventService;
        this.subjectService = subjectService;
        this.requestService = requestService;
        this.attendeeService = attendeeService;
    }

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/requests")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String showAllRequests(Model model) {
        List<RequestResponseDto> requests = requestService.getAll();
        List<Optional<EventResponseDto>> oldEvents = new ArrayList<>();
        for (RequestResponseDto r : requests) {
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
}
