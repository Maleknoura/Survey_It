package ma.wora.malek.survey.implementation;

import ma.wora.malek.survey.dto.chapter.ChapterResultDTO;
import ma.wora.malek.survey.dto.survey.SurveyResultsDTO;
import ma.wora.malek.survey.entity.Chapter;

import java.util.List;

public interface ISurveyResultsService {
    SurveyResultsDTO getSurveyResults(Long surveyId);
    List<ChapterResultDTO> processChapters(List<Chapter> chapters);
}