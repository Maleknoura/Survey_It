package com.wora.Survey.It.survey.application.dto.response;

import com.wora.Survey.It.survey.application.dto.nested.SubjectNestedDto;
import com.wora.Survey.It.survey.application.dto.nested.SurveyEdNestedDto;

import java.util.List;

public record SubjectResponseDto(
        Long id,
        String title,
        Long parentSubjectId,
        SurveyEdNestedDto surveyEdition,
        List<SubjectNestedDto> subSubjects
) {
}
