package ma.wora.malek.survey.repository;

import ma.wora.malek.survey.entity.SurveyEdition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyEditionRepository extends JpaRepository<SurveyEdition, Long> {
    List<SurveyEdition> findBySurveyId(Long surveyId);

}
