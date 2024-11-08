//package com.wora.Survey.It.survey.application.mapper;
//
//import com.wora.Survey.It.survey.application.dto.response.*;
//import com.wora.Survey.It.survey.application.dto.result.QuestionResultDto;
//import com.wora.Survey.It.survey.application.dto.result.SurveyResultsDto;
//import com.wora.Survey.It.survey.domain.entity.Question;
//import com.wora.Survey.It.survey.domain.entity.Subject;
//import com.wora.Survey.It.survey.domain.entity.Answer;
//
//import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Mapper(componentModel = "spring")
//public interface SurveyResultMapper {
//
//    @Mapping(source = "survey.title", target = "surveyTitle")
//    @Mapping(source = "subjects", target = "chapters", qualifiedByName = "mapChapters")
//    SurveyResultsDto mapToSurveyResultsDto(SurveyEdition surveyEdition);
//
//    @Named("mapChapters")
//    default List<SubjectResponseDto> mapChapters(List<Subject> subjects) {
//        return subjects.stream()
//                .filter(subject -> subject.getParentSubject() == null)
//                .map(this::mapToChapterDto)
//                .collect(Collectors.toList());
//    }
//
//    @Mapping(source = "title", target = "title")
//    @Mapping(source = "questions", target = "questions", qualifiedByName = "mapQuestions")
//    SubjectResponseDto mapToChapterDto(Subject subject);
//
//    @Named("mapQuestions")
//    default List<QuestionResultDto> mapQuestions(List<Question> questions) {
//        return questions.stream()
//                .map(this::mapToQuestionDto)
//                .collect(Collectors.toList());
//    }
//
//    @Mapping(source = "text", target = "question")
//    @Mapping(source = "answers", target = "answers")
//    QuestionResultDto mapToQuestionDto(Question question);
//
//    default Map<String, Integer> mapAnswers(List<Answer> answers) {
//        return answers.stream()
//                .collect(Collectors.toMap(
//                        Answer::getText,
//                        Answer::getSelectionCount
//                ));
//    }
//    @Mapping(source = "questions", target = "questions", qualifiedByName = "mapAnswersForQuestions")
//    SubSubjectResponseDto toSubSubjectDto(Subject savedSubject);
//
//}