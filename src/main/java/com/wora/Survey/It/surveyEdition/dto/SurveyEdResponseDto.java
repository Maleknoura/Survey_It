package com.wora.Survey.It.surveyEdition.dto;

import com.wora.Survey.It.subject.dto.SubjectResponseDto;

import java.time.LocalDate;
import java.util.List;

public record SurveyEdResponseDto(
        Long id,
        LocalDate creationDate,
        LocalDate startDate,
        LocalDate endDate,
        int year,
        Long surveyId,
        List<SubjectResponseDto> subjects

) {
}
