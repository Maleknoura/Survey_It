package com.wora.Survey.It.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequestDto(
        @NotBlank(message = "Answer text is required") String text,
        @NotNull(message = "Question ID is required") Long questionId
) {
}
