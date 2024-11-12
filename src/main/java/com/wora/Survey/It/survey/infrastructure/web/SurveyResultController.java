package com.wora.Survey.It.survey.infrastructure.web;

import com.wora.Survey.It.survey.application.dto.result.QuestionResultDto;
import com.wora.Survey.It.survey.application.dto.result.SubjectResultDto;
import com.wora.Survey.It.survey.application.dto.result.SurveyEditionResultDto;
import com.wora.Survey.It.survey.application.dto.result.SurveyResultsDto;
import com.wora.Survey.It.survey.application.mapper.SurveyResultMapper;
import com.wora.Survey.It.survey.application.service.SurveyResultService;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.entity.Survey;
import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import com.wora.Survey.It.survey.domain.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyResultController {

    private final SurveyResultService surveyService;

    @GetMapping("/{surveyId}/results")
    public ResponseEntity<SurveyResultsDto> getSurvey(@PathVariable Long surveyId) {
        Optional<SurveyResultsDto> surveyResultsDtoOpt = surveyService.getSurveyResults(surveyId);

        return surveyResultsDtoOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}





