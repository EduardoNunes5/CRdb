package com.psoft.crdb.services;

import com.psoft.crdb.dtos.SubjectRequestDTO;
import com.psoft.crdb.dtos.SubjectResponseDTO;
import com.psoft.crdb.mappers.SubjectMapper;
import com.psoft.crdb.models.Subject;
import com.psoft.crdb.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectService {

    private final SubjectMapper subjectMapper = SubjectMapper.INSTANCE;
    private final SubjectRepository subjectRepository;

    public SubjectResponseDTO createSubject(SubjectRequestDTO subjectRequestDTO) {
        verifyIfIsAlreadyRegistered(subjectRequestDTO.getName());

        Subject subject = this.subjectMapper.toSubject(subjectRequestDTO);
        Subject saveSubject = this.subjectRepository.save(subject);
        return this.subjectMapper.toSubjectResponseDTO(saveSubject);
    }

    private void verifyIfIsAlreadyRegistered(String name) throws EntityExistsException {
        Optional<Subject> optSavedSubject = subjectRepository.findSubjectByName(name);

        if (optSavedSubject.isPresent()) {
            throw new EntityExistsException("Subject already registered.");
        }

    }
}
