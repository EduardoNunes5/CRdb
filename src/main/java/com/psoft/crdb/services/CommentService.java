package com.psoft.crdb.services;

import com.psoft.crdb.dtos.CommentRequestDTO;
import com.psoft.crdb.dtos.CommentResponseDTO;
import com.psoft.crdb.mappers.CommentMapper;
import com.psoft.crdb.mappers.SubjectMapper;
import com.psoft.crdb.models.Comment;
import com.psoft.crdb.models.Subject;
import com.psoft.crdb.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final CommentRepository commentRepository;

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper = SubjectMapper.INSTANCE;


    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO){
        Comment toBeSaved = commentMapper.toComment(commentRequestDTO);
        toBeSaved.setTimestamp(LocalDateTime.now());
        Comment saved = this.commentRepository.save(toBeSaved);
        return commentMapper.toCommentResponseDTO(saved);
    }

    public CommentResponseDTO createComment(String name, CommentRequestDTO commentRequestDTO){
        Subject subject = subjectMapper.toSubject(subjectService.findByName(name));
        Comment comment = commentMapper.toComment(commentRequestDTO);

        subject.addComment(comment);
        subjectService.save(subject);

        return createComment(commentRequestDTO);
    }
}
