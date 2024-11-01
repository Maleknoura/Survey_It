package com.wora.Survey.It.owner.application.dto;

import com.wora.Survey.It.survey.application.dto.nested.SurveyNestedDto;

import java.util.List;

public record OwnerResponseDto(
        Long id,
        String name,
        List<SurveyNestedDto> surveys
) {
}
