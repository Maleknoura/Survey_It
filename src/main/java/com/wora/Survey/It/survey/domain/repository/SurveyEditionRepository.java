package com.wora.Survey.It.survey.domain.repository;

import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyEditionRepository extends JpaRepository<SurveyEdition,Long> {
}
