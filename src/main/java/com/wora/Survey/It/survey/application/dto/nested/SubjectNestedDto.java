package com.wora.Survey.It.survey.application.dto.nested;

import com.wora.Survey.It.survey.application.dto.response.SurveyEdResponseDto;

public record SubjectNestedDto(
        Long id,
        String title,
        Long parentSubjectId
) {
}
