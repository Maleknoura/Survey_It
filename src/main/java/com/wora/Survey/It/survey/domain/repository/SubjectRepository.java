package com.wora.Survey.It.survey.domain.repository;

import com.wora.Survey.It.survey.domain.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
