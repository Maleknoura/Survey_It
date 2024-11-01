package com.wora.Survey.It.survey.application.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SurveyEdRequestDto(
        @NotNull(message = "Creation date is required")
        LocalDate creationDate,

        @NotNull(message = "Start date is required")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        LocalDate endDate,

        @NotNull(message = "Year is required")
        int year,

        @NotNull(message = "Survey name is required")
        String surveyName,

        List<Long> subjectId
) {
}
