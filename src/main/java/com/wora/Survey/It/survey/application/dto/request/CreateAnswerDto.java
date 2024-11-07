package com.wora.Survey.It.survey.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAnswerDto(
        @NotBlank(message = "Answer text is required") String text,
        @NotNull(message = "Question ID is required") Long questionId
) {
}
