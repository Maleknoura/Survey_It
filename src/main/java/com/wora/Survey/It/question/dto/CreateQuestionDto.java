package com.wora.Survey.It.question.dto;

import com.wora.Survey.It.question.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateQuestionDto {
    @NotBlank(message = "Question text is required") String text;
    @NotNull(message = "Question type is required")
    QuestionType type;
    @NotNull(message = "Subject ID is required") Long subjectId;
}
