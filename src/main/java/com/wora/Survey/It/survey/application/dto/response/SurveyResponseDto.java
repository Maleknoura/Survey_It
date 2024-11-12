package com.wora.Survey.It.survey.application.dto.response;

import com.wora.Survey.It.owner.application.dto.OwnerNestedDto;

public record SurveyResponseDto(
        Long id,
        String title,
        String description,
        Boolean status
) {



}
