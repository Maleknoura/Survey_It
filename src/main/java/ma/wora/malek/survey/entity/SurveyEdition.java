package ma.wora.malek.survey.entity;

import jakarta.persistence.*;
import lombok.Data;
import ma.wora.malek.survey.util.BaseEntity;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class SurveyEdition extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate creationDate;
    private LocalDate startDate;
    private Integer year;

    @OneToMany(mappedBy = "surveyEdition", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;


}
