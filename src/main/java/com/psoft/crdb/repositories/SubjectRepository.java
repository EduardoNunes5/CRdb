package com.psoft.crdb.repositories;

import com.psoft.crdb.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findSubjectByName(String name);

}
