package com.wora.Survey.It.subject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SubjectRequestdto(
        @NotBlank(message = "Title is required") String title,
        @NotNull(message = "Survey Edition ID is required") Long surveyEditionId,
        Long parentSubjectId
) {
}
