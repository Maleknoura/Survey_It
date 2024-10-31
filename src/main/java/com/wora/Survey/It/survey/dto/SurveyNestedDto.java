package com.wora.Survey.It.survey.dto;

public record SurveyNestedDto(
        Long id,
        String title,
        String description,
        Boolean status
) {
}
