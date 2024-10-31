package com.wora.Survey.It.survey;

import com.wora.Survey.It.owner.OwnerMapper;
import com.wora.Survey.It.survey.dto.SurveyRequestDto;
import com.wora.Survey.It.survey.dto.SurveyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {OwnerMapper.class})
public interface SurveyMapper {
    @Mapping(source = "ownerId", target = "owner.id")
    Survey toEntity(SurveyRequestDto dto);

    @Mapping(source = "owner", target = "owner")
    SurveyResponseDto toDto(Survey survey);

    @Mapping(source = "ownerId", target = "owner.id")
    void updateEntityFromDto(SurveyRequestDto dto, @MappingTarget Survey existingSurvey);
}
