package com.wora.Survey.It.survey.application.dto.response;

import jakarta.validation.constraints.NotBlank;

public record AnswerDto(
        @NotBlank(message = "L'identifiant de la réponse ne peut pas être vide.")
        String answerId

) {
}
