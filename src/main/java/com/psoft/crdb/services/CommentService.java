package com.psoft.crdb.services;

import com.psoft.crdb.dtos.CommentRequestDTO;
import com.psoft.crdb.dtos.CommentResponseDTO;
import com.psoft.crdb.mappers.CommentMapper;
import com.psoft.crdb.models.Comment;
import com.psoft.crdb.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private final CommentMapper commentMapper = CommentMapper.INSTANCE;
    private final CommentRepository commentRepository;

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO){
        Comment toBeSaved = commentMapper.toComment(commentRequestDTO);
        toBeSaved.setTimestamp(LocalDateTime.now());
        Comment saved = this.commentRepository.save(toBeSaved);
        return commentMapper.toCommentResponseDTO(saved);
    }
}
