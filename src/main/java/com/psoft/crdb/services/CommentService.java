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

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final CommentRepository commentRepository;

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper = SubjectMapper.INSTANCE;

    public CommentResponseDTO createComment(String name, CommentRequestDTO commentRequestDTO){
        Subject subject = subjectMapper.toSubject(subjectService.findByName(name));
        Comment comment = commentMapper.toComment(commentRequestDTO);
        comment.setTimestamp(LocalDateTime.now());
        subject.addComment(comment);
        subjectService.save(subject);

        return commentMapper.toCommentResponseDTO(comment);
    }

    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO){
        Subject subject = subjectService.verifyAndGetIfExists(commentRequestDTO.getSubject());
        Comment toComment = commentMapper.toComment(commentRequestDTO);
        Comment foundComment = findByIdAndSubject(id, subject);

        toComment.setSubject(foundComment.getSubject());
        toComment.setId(foundComment.getId());
        toComment.setSubject(foundComment.getSubject());
        toComment.setTimestamp(foundComment.getTimestamp());

        return commentMapper.toCommentResponseDTO(commentRepository.save(toComment));
    }

    private Comment findByIdAndSubject(Long id, Subject subject){
        return commentRepository.findByIdAndSubject(id, subject)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }
}
