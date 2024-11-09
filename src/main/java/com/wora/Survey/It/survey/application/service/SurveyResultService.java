package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.survey.application.dto.result.SurveyResultsDto;
import com.wora.Survey.It.survey.application.dto.result.SurveyEditionResultDto;
import com.wora.Survey.It.survey.application.dto.result.SubjectResultDto;
import com.wora.Survey.It.survey.application.dto.result.QuestionResultDto;
import com.wora.Survey.It.survey.application.mapper.SurveyResultMapper;
import com.wora.Survey.It.survey.domain.entity.Survey;
import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyResultService {

    private final SurveyRepository surveyRepository;
    private final SurveyResultMapper surveyResultMapper;



    public Optional<SurveyResultsDto> getSurveyResults(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .map(surveyResultMapper::toSurveyResultsDto);
    }
}
