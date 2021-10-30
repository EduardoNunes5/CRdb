package com.psoft.crdb.controllers;

import com.psoft.crdb.dtos.CommentRequestDTO;
import com.psoft.crdb.dtos.CommentResponseDTO;
import com.psoft.crdb.services.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public CommentResponseDTO createComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO){
        return commentService.createComment(commentRequestDTO);
    }

}
