package com.wora.Survey.It.survey.application.dto.nested;

import com.wora.Survey.It.survey.domain.enums.QuestionType;
import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;

public record NestedQuestionDto(
        Long id,
        String text,
        QuestionType type,
        int answerCount

) {
}
