package com.wora.Survey.It.owner.dto;

import com.wora.Survey.It.survey.dto.SurveyResponseDto;

import java.util.List;

public record OwnerResponseDto(
        Long id,
        String name,
        List<SurveyResponseDto> surveys
) {
}
