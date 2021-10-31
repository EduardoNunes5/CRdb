package com.psoft.crdb.mappers;

import com.psoft.crdb.dtos.SubjectRequestDTO;
import com.psoft.crdb.dtos.SubjectResponseDTO;
import com.psoft.crdb.models.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toSubject(SubjectRequestDTO subjectRequestDTO);
    SubjectResponseDTO toSubjectResponseDTO(Subject subject);
}
