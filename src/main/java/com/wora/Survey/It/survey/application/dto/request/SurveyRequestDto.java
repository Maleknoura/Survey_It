package com.wora.Survey.It.survey.application.dto.request;

import com.wora.Survey.It.common.Validation.Exists;
import com.wora.Survey.It.survey.domain.entity.Survey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveyRequestDto(
        @NotBlank(message = "Title is required")
        @Exists(entity = Survey.class, field = "title", message = "Le titre du survey doit être unique")
        String title,
        @NotBlank(message="description required")
        String description,

        Boolean status,
       Long ownerId
) {
}
