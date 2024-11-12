package com.wora.Survey.It.survey.domain.repository;

import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey,Long> {


}

