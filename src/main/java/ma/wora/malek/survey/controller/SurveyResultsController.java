package ma.wora.malek.survey.controller;

import lombok.RequiredArgsConstructor;
import ma.wora.malek.survey.dto.api.ApiResponseDTO;
import ma.wora.malek.survey.dto.survey.SurveyResultsDTO;
import ma.wora.malek.survey.service.SurveyResultsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surveys/editions")
@RequiredArgsConstructor
public class SurveyResultsController {
    private final SurveyResultsService surveyResultsService;

    @GetMapping("/{editionId}/results")
    public ResponseEntity<ApiResponseDTO<SurveyResultsDTO>> getSurveyResults(@PathVariable Long editionId) {
        SurveyResultsDTO results = surveyResultsService.getSurveyResults(editionId);
        return ResponseEntity.ok(ApiResponseDTO.success(results));
    }

}
