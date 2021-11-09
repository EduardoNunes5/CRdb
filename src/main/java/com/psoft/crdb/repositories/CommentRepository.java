package com.psoft.crdb.repositories;

import com.psoft.crdb.models.Comment;
import com.psoft.crdb.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndSubject(Long id, Subject subject);
}
