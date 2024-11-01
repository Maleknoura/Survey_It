package com.wora.Survey.It.survey.application.dto.nested;

public record SurveyNestedDto(
        Long id,
        String title,
        String description,
        Boolean status
) {
}
