package ma.wora.malek.survey.dto.survey;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ma.wora.malek.survey.entity.Owner;
import ma.wora.malek.survey.util.BaseDTO;
import ma.wora.malek.survey.validation.annotation.Exists;

@Data
public class CreateSurveyRequestDTO extends BaseDTO {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Owner ID is required")
    @Positive(message = "Owner ID must be a positive number")
    @Exists(
            entity = Owner.class,
            message = "Owner with this ID doesn't exist"
    )
    private Long ownerId;

}
