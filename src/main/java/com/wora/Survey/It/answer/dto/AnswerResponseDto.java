package com.wora.Survey.It.answer.dto;

import com.wora.Survey.It.question.dto.QuestionResponseDto;

public record AnswerResponseDto(
        Long id,
        String text,
        QuestionResponseDto question
) {
}
