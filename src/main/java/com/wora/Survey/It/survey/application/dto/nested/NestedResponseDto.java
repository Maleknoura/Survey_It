package com.wora.Survey.It.survey.application.dto.nested;

import com.wora.Survey.It.survey.domain.enums.QuestionType;

public record NestedResponseDto(
        Long id,
        String text,
        QuestionType type,
        int answerCount
) {
}
