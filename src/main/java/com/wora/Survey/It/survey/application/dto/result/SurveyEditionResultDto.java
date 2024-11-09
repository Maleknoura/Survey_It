package com.wora.Survey.It.survey.application.dto.result;

import java.util.List;

public record SurveyEditionResultDto(int year, List<SubjectResultDto> subjects) {
}
