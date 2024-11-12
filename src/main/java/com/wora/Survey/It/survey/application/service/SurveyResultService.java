package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.survey.application.dto.result.SurveyResultsDto;
import com.wora.Survey.It.survey.application.mapper.SurveyResultMapper;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Survey;
import com.wora.Survey.It.survey.domain.repository.SurveyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyResultService {

    private final SurveyRepository surveyRepository;
    private final SurveyResultMapper surveyResultMapper;

    @Transactional
    public Optional<SurveyResultsDto> getSurveyResults(Long surveyId) {
        Optional<Survey> result = surveyRepository.findById(surveyId);
        result.ifPresent(s -> {
            List<Question> list = s.getSurveyEditions().stream()
                    .flatMap(se -> se.getSubjects().stream())
                    .flatMap(subject -> subject.getQuestions().stream())
                    .toList();

            System.out.println("Number of questions: " + list.size());
            System.out.println("Questions: " + list);
        });
        return result.map(surveyResultMapper::toSurveyResultsDto);
    }

}
