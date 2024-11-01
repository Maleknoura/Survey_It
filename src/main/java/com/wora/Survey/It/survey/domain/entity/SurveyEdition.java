package com.wora.Survey.It.survey.domain.entity;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveyEdition", fetch = FetchType.EAGER)
    private List<Subject> subjects;


}



