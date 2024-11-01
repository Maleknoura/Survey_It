package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerResponseDto;
import com.wora.Survey.It.survey.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    Answer toEntity(AnswerRequestDto dto);

    AnswerResponseDto toDto(Answer entity);

    void updateEntityFromDto(AnswerRequestDto dto, @MappingTarget Answer existingAnswer);
}
