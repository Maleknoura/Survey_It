package com.wora.Survey.It.survey.application.dto.response;

import java.util.List;

public record ResponseDto(
       Long questionId,
         String answerId,
       List<AnswerDto> answers
) {
}
