package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.response.*;
import com.wora.Survey.It.survey.application.dto.result.*;
import com.wora.Survey.It.survey.domain.entity.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")

public interface SurveyResultMapper {


    @Mapping(target = "title", source = "title")
    @Mapping(target = "surveyEditions", expression = "java(mapSurveyEditions(survey.getSurveyEditions()))")
    SurveyResultsDto toSurveyResultsDto(Survey survey);

    default List<SurveyEditionResultDto> mapSurveyEditions(List<SurveyEdition> surveyEditions) {
        if (surveyEditions == null) return List.of();
        return surveyEditions.stream()
                .map(this::mapSurveyEdition)
                .collect(Collectors.toList());
    }

    default SurveyEditionResultDto mapSurveyEdition(SurveyEdition surveyEdition) {
        return new SurveyEditionResultDto(
                surveyEdition.getYear(),
                mapSubjects(surveyEdition.getSubjects())
        );
    }

    default List<SubjectResultDto> mapSubjects(List<Subject> subjects) {
        if (subjects == null) return List.of();
        return subjects.stream()
                .map(this::mapSubject)
                .collect(Collectors.toList());
    }

    default SubjectResultDto mapSubject(Subject subject) {
        List<QuestionResultDto> questionResultDtos = (subject.getQuestions() == null || subject.getQuestions().isEmpty())
                ? List.of(new QuestionResultDto("Question not available", Map.of("exampleResponse", 0), 0))
                : subject.getQuestions().stream().map(this::mapQuestion).collect(Collectors.toList());

        return new SubjectResultDto(subject.getTitle(), questionResultDtos);
    }

    default QuestionResultDto mapQuestion(Question question) {
        String questionText = (question.getText() != null && !question.getText().isEmpty())
                ? question.getText()
                : "";

        Map<String, Integer> responses = (question.getAnswers() != null)
                ? question.getAnswers().stream()
                .collect(Collectors.toMap(Answer::getText, Answer::getSelectionCount))
                : Map.of();

        int totalAnswers = responses.values().stream().mapToInt(Integer::intValue).sum();

        return new QuestionResultDto(questionText, responses, totalAnswers);
    }


}