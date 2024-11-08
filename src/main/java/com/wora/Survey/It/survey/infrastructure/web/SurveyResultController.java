//package com.wora.Survey.It.survey.infrastructure.web;
//
//import com.wora.Survey.It.survey.application.dto.result.SurveyResultsDto;
//import com.wora.Survey.It.survey.application.mapper.SurveyResultMapper;
//import com.wora.Survey.It.survey.application.service.SurveyResultService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/surveys")
//@RequiredArgsConstructor
//public class SurveyResultController {
//
//    private final SurveyResultService surveyService;
//    private final SurveyResultMapper surveyResultMapper;
//
//    @GetMapping("/{surveyId}/results")
//    public ResponseEntity<SurveyResultsDto> getSurveyResults(@PathVariable Long surveyId) {
//        Optional<SurveyResultsDto> results = surveyService.getSurveyResults(surveyId);
//        return results.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//}
