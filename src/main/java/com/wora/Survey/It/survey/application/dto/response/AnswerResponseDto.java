package com.wora.Survey.It.survey.application.dto.response;

import com.wora.Survey.It.survey.application.dto.nested.NestedQuestionDto;

public record AnswerResponseDto(
        Long id,
        String text,
        NestedQuestionDto question
) {
}
