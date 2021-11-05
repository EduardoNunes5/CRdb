package com.psoft.crdb.controllers;

import com.psoft.crdb.dtos.CommentRequestDTO;
import com.psoft.crdb.dtos.CommentResponseDTO;
import com.psoft.crdb.mappers.CommentMapper;
import com.psoft.crdb.mappers.SubjectMapper;
import com.psoft.crdb.models.Comment;
import com.psoft.crdb.models.Subject;
import com.psoft.crdb.services.CommentService;
import com.psoft.crdb.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    @PostMapping("subject/{name}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDTO createComment(@PathVariable("name") String name,
                                            @RequestBody @Valid CommentRequestDTO commentRequestDTO){

        return this.commentService.createComment(name, commentRequestDTO);
    }

}
