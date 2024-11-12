package com.wora.Survey.It.survey.application.dto.result;

import java.util.List;

public record SurveyResultsDto(
       String title,
       List<SurveyEditionResultDto> editions
) {

}
