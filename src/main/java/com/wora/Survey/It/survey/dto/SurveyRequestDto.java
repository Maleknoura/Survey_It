package com.wora.Survey.It.survey.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyRequestDto(
        @NotBlank(message = "Title is required") String title,
        String description,
        Boolean status,
        @NotNull(message = "Owner ID is required") Long ownerId
) {
}
