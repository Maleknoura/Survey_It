package com.wora.Survey.It.survey.application.dto.result;

import java.util.List;

public record SubjectResultDto(
        String title,
        List<QuestionResultDto> questions,
        List<SubjectResultDto> subSubjects
) {
}
