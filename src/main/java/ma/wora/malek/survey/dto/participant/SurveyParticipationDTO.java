package ma.wora.malek.survey.dto.participant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ma.wora.malek.survey.dto.question.QuestionResponseDTO;

import java.util.List;


@Data
public class SurveyParticipationDTO {
    @Valid
    private List<QuestionResponseDTO> responses;
}