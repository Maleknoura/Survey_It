package com.wora.Survey.It.survey.infrastructure.web;

import com.wora.Survey.It.survey.application.dto.request.QuestionRequestDto;
import com.wora.Survey.It.survey.application.dto.response.QuestionResponseDto;
import com.wora.Survey.It.survey.application.service.QuestionServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subchapters/{subChapterId}/questions ")
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponseDto> createQuestion(
            @Valid @RequestBody QuestionRequestDto requestDto
    ) {
        QuestionResponseDto responseDto = questionService.save(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }
}