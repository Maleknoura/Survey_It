package com.wora.Survey.It.survey.domain.repository;

import com.wora.Survey.It.survey.domain.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
