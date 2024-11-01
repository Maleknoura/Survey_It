package com.wora.Survey.It.survey.domain.repository;

import com.wora.Survey.It.survey.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
}
