package com.wora.Survey.It.answer;

import com.wora.Survey.It.answer.dto.AnswerRequestDto;
import com.wora.Survey.It.answer.dto.AnswerResponseDto;
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
