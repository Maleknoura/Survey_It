package com.wora.Survey.It.survey.application.dto.result;

import com.wora.Survey.It.survey.application.dto.nested.NestedAnswerDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerResponseDto;

import java.util.List;
import java.util.Map;

public record QuestionResultDto(
        String question,
        List<NestedAnswerDto> answers

) {
}
