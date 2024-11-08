package com.wora.Survey.It.survey.application.dto.result;

import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;

import java.util.List;

public record SurveyResultsDto(
        String surveyTitle,
        List<SubjectResponseDto> chapters
) {}
