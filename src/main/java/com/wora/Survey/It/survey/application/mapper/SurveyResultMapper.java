package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.result.*;
import com.wora.Survey.It.survey.domain.entity.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = { SurveyEditionMapper.class, SubjectMapper.class, QuestionMapper.class, AnswerMapper.class })
public interface SurveyResultMapper {

    SurveyResultMapper INSTANCE = Mappers.getMapper(SurveyResultMapper.class);

    @Mapping(source = "editions", target = "editions")
    @Mapping(source = "title", target = "title")
    SurveyResultsDto toSurveyResultsDto(Survey survey);

    @Mapping(source = "answers", target = "answers", qualifiedByName = "mapAnswersToMap")
    QuestionResultDto toQuestionResultDto(Question question);

    AnswerResultDto toAnswerResultDto(Answer answer);
}
