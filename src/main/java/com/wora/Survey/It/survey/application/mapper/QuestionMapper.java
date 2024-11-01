package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.request.QuestionRequestDto;
import com.wora.Survey.It.survey.application.dto.response.QuestionResponseDto;
import com.wora.Survey.It.survey.domain.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface QuestionMapper {
    QuestionMapper Instance = Mappers.getMapper(QuestionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", ignore = true)
    Question toEntity(QuestionRequestDto requestDto);

    QuestionResponseDto toDto(Question question);

    void updateEntityFromDto(AnswerRequestDto dto, @MappingTarget Question existingQuestion);
}
