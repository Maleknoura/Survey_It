//package com.wora.Survey.It.survey.application.service;
//
//import com.wora.Survey.It.survey.application.dto.result.SurveyResultsDto;
//import com.wora.Survey.It.survey.application.mapper.SurveyResultMapper;
//import com.wora.Survey.It.survey.domain.repository.SurveyRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//
//public class SurveyResultService {
//    private final SurveyRepository surveyRepository;
//    private final SurveyResultMapper surveyResultMapper;
//
//    public SurveyResultService(SurveyRepository surveyRepository, SurveyResultMapper surveyResultMapper) {
//        this.surveyRepository = surveyRepository;
//        this.surveyResultMapper = surveyResultMapper;
//    }
//
//    public Optional<SurveyResultsDto> getSurveyResults(Long surveyId) {
//        return surveyRepository.findById(surveyId)
//                .flatMap(surveyRepository::findCurrentEdition)
//                .map(surveyResultMapper::mapToSurveyResultsDto);
//    }
//}