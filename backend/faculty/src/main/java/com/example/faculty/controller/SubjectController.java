package com.example.faculty.controller;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.models.requests.SubjectRequest;
import com.example.faculty.services.interfaces.SubjectService;
import com.example.faculty.util.mvc_responses.model.MvcResponse;
import com.example.faculty.util.mvc_responses.model.MvcResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public MvcResponse createSubject(@RequestBody SubjectRequest subjectRequest) {
        return new MvcResponseObject(200, subjectService.createSubject(subjectRequest));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public MvcResponse updateSubject(@RequestParam("subject_id") Long subjectId, @RequestBody SubjectRequest subjectRequest) {
        return new MvcResponseObject(200, subjectService.updateSubject(subjectId, subjectRequest));
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public MvcResponse getSubjectInfoById(@RequestParam("subject_id") Long subjectId) {
        return new MvcResponseObject(200, subjectService.getSubjectById(subjectId));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public MvcResponse deleteSubjectById(@RequestParam("subject_id") Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return new MvcResponse(200);
    }

    @RequestMapping(value = "/get_by_faculty", method = RequestMethod.POST)
    public MvcResponse getSubjectsByFaculty(@RequestParam("faculty") String faculty,
                                            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(value = "size", defaultValue = "10", required = false) int size) {
        return new MvcResponseObject(200, subjectService.getAllSubjectsByFaculty(faculty, PageRequest.of(page, size)));
    }
}
