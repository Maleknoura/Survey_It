package com.wora.Survey.It.survey.application.dto.response;

import com.wora.Survey.It.survey.application.dto.nested.NestedAnswerDto;
import com.wora.Survey.It.survey.domain.enums.QuestionType;

import java.util.List;

public record QuestionResponseDto(
        Long id,
        String text,
        QuestionType type,
        int answerCount,
        SubjectResponseDto subject,
        List<NestedAnswerDto> answers

) {
}
