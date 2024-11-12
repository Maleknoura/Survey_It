package com.wora.Survey.It.survey.application.dto.result;

import java.util.Map;

public record QuestionResultDto(
        String text, Map<String, Integer> answers, int answerCount
) {

}
