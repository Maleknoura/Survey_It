package com.wora.Survey.It.subject.dto;

import com.wora.Survey.It.surveyEdition.dto.SurveyEdResponseDto;

public record SubjectNestedDto(
        Long id,
        String title,
        SurveyEdResponseDto surveyEdition,
        Long parentSubjectId
) {
}
