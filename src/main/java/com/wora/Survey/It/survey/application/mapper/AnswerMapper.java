package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.request.CreateAnswerDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerResponseDto;
import com.wora.Survey.It.survey.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnswerMapper {

    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    Answer toEntity(AnswerRequestDto dto);

    AnswerResponseDto toAnswerResponseDto(Answer entity);

    Answer toEntity(CreateAnswerDto dto);

    AnswerDto toAnswerDto(Answer entity);

    void updateEntityFromDto(CreateAnswerDto dto, @MappingTarget Answer existingAnswer);
}
