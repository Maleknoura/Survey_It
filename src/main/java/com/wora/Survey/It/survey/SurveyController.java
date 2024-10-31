package com.wora.Survey.It.survey;

import com.wora.Survey.It.answer.AnswerServiceImpl;
import com.wora.Survey.It.answer.dto.AnswerRequestDto;
import com.wora.Survey.It.answer.dto.AnswerResponseDto;
import com.wora.Survey.It.survey.dto.SurveyRequestDto;
import com.wora.Survey.It.survey.dto.SurveyResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/survey")
public class SurveyController {

    private final SurveyServiceImpl surveyService;
    private final SurveyMapper surveyMapper;

    @Autowired
    public SurveyController(SurveyMapper surveyMapper, SurveyServiceImpl surveyService) {
        this.surveyMapper = surveyMapper;
        this.surveyService = surveyService;
        System.out.println("this component is initialezed");
    }

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
}
