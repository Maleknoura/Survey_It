package com.wora.Survey.It.survey.dto;

import com.wora.Survey.It.common.Validation.Exists;
import com.wora.Survey.It.survey.Survey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyRequestDto(
        @NotBlank(message = "Title is required")
        @Exists(entity = Survey.class, field = "title", message = "Le titre du survey doit être unique")
        String title,
        @NotBlank(message="description required")
        String description,

        Boolean status,
        @NotNull(message = "Owner ID is required") Long ownerId
) {
}
