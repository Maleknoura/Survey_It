package com.wora.Survey.It.survey.application.dto.response;

import com.wora.Survey.It.survey.application.dto.nested.SubjectNestedDto;

import java.time.LocalDate;
import java.util.List;

public record SurveyEdResponseDto(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        LocalDate endDate,
        int year,
        Long surveyId,
        List<SubjectNestedDto> subjects

) {
}
