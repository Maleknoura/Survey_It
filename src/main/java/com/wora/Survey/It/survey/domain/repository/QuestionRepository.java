package com.wora.Survey.It.survey.domain.repository;

import com.wora.Survey.It.survey.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
