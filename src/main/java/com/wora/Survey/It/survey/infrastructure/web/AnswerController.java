package com.wora.Survey.It.survey.infrastructure.web;

import com.wora.Survey.It.common.Validation.Exists;
import com.wora.Survey.It.survey.application.dto.request.CreateAnswerDto;
import com.wora.Survey.It.survey.application.service.AnswerServiceImpl;
import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys/{surveyId}/survey-editions/{surveyEditionId}/subjects/{subjectId}/subsubjects/{subSubjectId}/questions/{questionId}")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    @PostMapping
    public ResponseEntity<AnswerResponseDto> createAnswer(@RequestBody @Valid CreateAnswerDto createAnswerDto) {
        AnswerResponseDto createdAnswer = answerService.save(createAnswerDto);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AnswerResponseDto>> findAnswers() {
        List<AnswerResponseDto> answers = answerService.findAll();
        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> getAnswerById(@PathVariable Long id) {
        return answerService.findById(id)
                .map(answer -> new ResponseEntity<>(answer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponseDto> updateAnswer(
            @PathVariable Long id,
            @Valid @RequestBody CreateAnswerDto createAnswerDto) {
        AnswerResponseDto updatedResponse = answerService.update(createAnswerDto, id);
        if (updatedResponse != null) {
            return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        answerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

