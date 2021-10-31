package com.psoft.crdb.controllers;

import com.psoft.crdb.dtos.SubjectRequestDTO;
import com.psoft.crdb.dtos.SubjectResponseDTO;
import com.psoft.crdb.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RestController
@RequestMapping("api/subjects")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponseDTO createSubject(@RequestBody @Valid SubjectRequestDTO subjectRequestDTO) {
        return subjectService.createSubject(subjectRequestDTO);
    }


}
