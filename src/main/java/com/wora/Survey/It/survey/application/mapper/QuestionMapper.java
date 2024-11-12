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


    Question toEntity(QuestionRequestDto requestDto);

    @Mapping(target = "id", source = "question.id")
    @Mapping(target = "subject", source = "question.subject")
    @Mapping(target = "answerCount", source = "question.answerCount")
    @Mapping(target = "answers", source = "question.answers")
    QuestionResponseDto toDto(Question question);

    @Mapping(target = "answers", source = "dto.responses")
    void updateEntityFromDto(AnswerRequestDto dto, @MappingTarget Question existingQuestion);
}
