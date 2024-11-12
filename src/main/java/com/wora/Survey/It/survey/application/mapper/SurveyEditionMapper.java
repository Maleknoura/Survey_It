package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.result.QuestionResultDto;
import com.wora.Survey.It.survey.application.dto.result.SubjectResultDto;
import com.wora.Survey.It.survey.application.dto.result.SurveyEditionResultDto;
import com.wora.Survey.It.survey.domain.entity.Answer;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        SubjectMapper.class,
        QuestionMapper.class,
        AnswerMapper.class
})
public interface SurveyEditionMapper {

    SurveyEditionMapper INSTANCE = Mappers.getMapper(SurveyEditionMapper.class);

    @Mapping(source = "year", target = "year")
    @Mapping(source = "subjects", target = "subjects")
    SurveyEditionResultDto toSurveyEditionResultDto(SurveyEdition surveyEdition);

    @Mapping(source = "answers", target = "answers", qualifiedByName = "mapAnswersToMap")
    QuestionResultDto toQuestionResultDto(Question question);

    @Mapping(source = "questions", target = "questions")

    SubjectResultDto toSubjectResultDto(Subject subject);

    @Named("mapAnswersToMap")
    default Map<String, Integer> mapAnswersToMap(List<Answer> answers) {
        return answers.stream()
                .collect(Collectors.toMap(Answer::getText, Answer::getSelectionCount));
    }
}
