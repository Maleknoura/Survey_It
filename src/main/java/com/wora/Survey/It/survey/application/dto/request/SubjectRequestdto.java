package com.wora.Survey.It.survey.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public record SubjectRequestdto(
        @NotBlank(message = "Title is required") String title,
        @NotNull(message = "Survey Edition ID is required")
        Long surveyEditionId,
        Long parentSubjectId
) {
    public Long getSurveyEditionId() {
        return surveyEditionId;
    }
}
