package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.owner.application.mapper.OwnerMapper;
import com.wora.Survey.It.survey.application.dto.request.SurveyRequestDto;
import com.wora.Survey.It.survey.application.dto.response.SurveyResponseDto;
import com.wora.Survey.It.survey.domain.entity.Survey;
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
