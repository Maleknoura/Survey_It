package ma.wora.malek.survey.mapper;

import ma.wora.malek.survey.dto.surveyEdition.CreateSurveyEditionRequestDTO;
import ma.wora.malek.survey.dto.surveyEdition.SurveyEditionResponseDTO;
import ma.wora.malek.survey.dto.surveyEdition.UpdateSurveyEditionRequestDTO;
import ma.wora.malek.survey.entity.SurveyEdition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring", uses = {ChapterMapper.class, SurveyMapper.class})
public interface SurveyEditionMapper {

    @Mapping(target = "surveyId", source = "survey.id")
    SurveyEditionResponseDTO toDto(SurveyEdition surveyEdition);

    @Mapping(target = "survey.id", source = "surveyId")
    SurveyEdition toEntity(CreateSurveyEditionRequestDTO createSurveyEditionRequestDTO);

    CreateSurveyEditionRequestDTO toCreateDto(SurveyEdition surveyEdition);

    @Mapping(target = "survey.id", source = "surveyId")
    void updateEntityFromUpdateDto(UpdateSurveyEditionRequestDTO updateDto, @MappingTarget SurveyEdition entity);

}
