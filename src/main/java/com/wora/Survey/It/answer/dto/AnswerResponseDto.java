package com.wora.Survey.It.answer.dto;

import com.wora.Survey.It.question.dto.NestedQuestionDto;

public record AnswerResponseDto(
        Long id,
        String text,
        NestedQuestionDto question
) {
}
