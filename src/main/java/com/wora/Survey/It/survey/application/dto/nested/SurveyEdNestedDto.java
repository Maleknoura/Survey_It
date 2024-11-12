package com.wora.Survey.It.survey.application.dto.nested;

import java.time.LocalDate;

public record SurveyEdNestedDto(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        LocalDate endDate,
        int year,
        Long surveyId
) {

}
