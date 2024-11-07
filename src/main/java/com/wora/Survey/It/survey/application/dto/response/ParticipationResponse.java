package com.wora.Survey.It.survey.application.dto.response;

import java.time.LocalDateTime;

public record ParticipationResponse(
        Long surveyId,
        String text,
        LocalDateTime timestamp
) {
}
