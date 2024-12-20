package ma.wora.malek.survey.implementation;

import ma.wora.malek.survey.dto.participant.SurveyParticipationDTO;

public interface ISurveyParticipationService {
    void saveParticipationResponses(Long surveyId, SurveyParticipationDTO participationDTO);
}