package com.wora.Survey.It.surveyEdition;

import com.wora.Survey.It.subject.Subject;
import com.wora.Survey.It.survey.Survey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SurveyEdition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate creationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private int year;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveyEdition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;


}



