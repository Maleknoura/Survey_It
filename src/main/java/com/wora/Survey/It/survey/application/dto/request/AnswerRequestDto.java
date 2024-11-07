package com.wora.Survey.It.survey.application.dto.request;

import com.wora.Survey.It.survey.application.dto.response.ResponseDto;

import java.util.List;

public record AnswerRequestDto(
        List<ResponseDto> responses
) {
}
