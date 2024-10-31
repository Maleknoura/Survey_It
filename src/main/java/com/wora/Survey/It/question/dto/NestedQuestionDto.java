package com.wora.Survey.It.question.dto;

import com.wora.Survey.It.question.QuestionType;
import com.wora.Survey.It.subject.dto.SubjectResponseDto;

public record NestedQuestionDto(
        Long id,
        String text,
        QuestionType type,
        int answerCount,
        SubjectResponseDto subject
) {
}
