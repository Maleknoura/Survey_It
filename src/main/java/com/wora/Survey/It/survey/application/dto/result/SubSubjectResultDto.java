package com.wora.Survey.It.survey.application.dto.result;

import java.util.List;

public record SubSubjectResultDto(
        String title,
        List<QuestionResultDto> questions
) {
}
