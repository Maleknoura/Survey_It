package com.wora.Survey.It.survey.application.dto.result;

public record AnswerResultDto(
        String text,
        int selectionCount,
        Double percentage
) {
}
