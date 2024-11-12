package com.wora.Survey.It.survey.infrastructure.web;

import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.mapper.SurveyMapper;
import com.wora.Survey.It.survey.application.service.SurveyServiceImpl;
import com.wora.Survey.It.survey.application.dto.request.SurveyRequestDto;
import com.wora.Survey.It.survey.application.dto.response.SurveyResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyServiceImpl surveyService;
    private final SurveyMapper surveyMapper;


    @PostMapping
    public ResponseEntity<SurveyResponseDto> createSurvey(@RequestBody @Valid SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto createdSurvey = surveyService.save(surveyRequestDto);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SurveyResponseDto>> findAllSurveys() {
        List<SurveyResponseDto> surveys = surveyService.findAll();
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> getSurveyById(@PathVariable Long id) {
        return surveyService.findById(id)
                .map(survey -> new ResponseEntity<>(survey, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> updateSurvey(@PathVariable Long id, @Valid @RequestBody SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto updatedSurvey = surveyService.update(surveyRequestDto, id);
        return new ResponseEntity<>(updatedSurvey, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        if (!surveyService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        surveyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/surveys/{surveyId}/participate")
    public ResponseEntity<String> participateInSurvey(@PathVariable Long surveyId,
                                                      @RequestBody AnswerRequestDto participationDto) {
        try {
            surveyService.saveParticipation(surveyId, participationDto);
            return ResponseEntity.ok("Participation saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving participation: " + e.getMessage());
        }
    }



}
