package com.wora.Survey.It.subject.dto;

import com.wora.Survey.It.surveyEdition.dto.SurveyEdResponseDto;

import java.util.List;

public record SubjectResponseDto(
        Long id,
        String title,
        SurveyEdResponseDto surveyEdition,
        Long parentSubjectId,
        List<SubjectResponseDto> subSubjects
) {
}
