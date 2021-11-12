package com.psoft.crdb.mappers;

import com.psoft.crdb.dtos.CommentRequestDTO;
import com.psoft.crdb.dtos.CommentResponseDTO;
import com.psoft.crdb.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source="subject", target = "subject.name")
    Comment toComment(CommentRequestDTO commentRequestDTO);
    CommentResponseDTO toCommentResponseDTO(Comment comment);
}
