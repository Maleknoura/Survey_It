package com.wora.Survey.It.survey.dto;

import com.wora.Survey.It.owner.dto.OwnerNestedDto;
import com.wora.Survey.It.owner.dto.OwnerResponseDto;

import java.util.List;

public record SurveyResponseDto(
        Long id,
        String title,
        String description,
        Boolean status,
        OwnerNestedDto owner
) {
}
