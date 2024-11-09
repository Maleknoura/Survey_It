package com.wora.Survey.It.survey.application.dto.result;

import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;
import com.wora.Survey.It.survey.domain.entity.Survey;

import java.util.List;

public record SurveyResultsDto(
       String title,
       List<SurveyEditionResultDto> surveyEditions
) {

}
