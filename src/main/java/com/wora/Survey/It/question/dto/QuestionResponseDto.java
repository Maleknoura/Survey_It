package com.wora.Survey.It.question.dto;

import com.wora.Survey.It.answer.dto.AnswerResponseDto;
import com.wora.Survey.It.question.QuestionType;
import com.wora.Survey.It.subject.dto.SubjectResponseDto;

import java.util.List;

public record QuestionResponseDto(
        Long id,
        String text,
        QuestionType type,
        int answerCount,
        SubjectResponseDto subject,
        List<AnswerResponseDto> answers

) {
}
