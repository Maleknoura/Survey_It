package ma.wora.malek.survey.dto.survey;

import lombok.Data;

@Data
public class SurveyEditionWithQuestionCountDTO {
    private Long id;
    private Integer year;
    private int questionCount;
}
