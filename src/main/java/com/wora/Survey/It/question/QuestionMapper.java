package com.wora.Survey.It.question;

import com.wora.Survey.It.answer.dto.AnswerRequestDto;
import com.wora.Survey.It.question.dto.QuestionRequestDto;
import com.wora.Survey.It.question.dto.QuestionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface QuestionMapper {
    QuestionMapper Instance= Mappers.getMapper(QuestionMapper.class);
            Question toEntity(QuestionRequestDto dto);
            QuestionResponseDto toDto(Question Entity);
           void updateEntityFromDto(AnswerRequestDto dto, @MappingTarget Question existingQuestion);
}
