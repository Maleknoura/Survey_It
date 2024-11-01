package com.wora.Survey.It.survey.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "survey_edition_id")
    private SurveyEdition surveyEdition;

    @ManyToOne
    @JoinColumn(name = "parent_subject_id")
    private Subject parentSubject;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parentSubject")
    private List<Subject> subSubjects = new ArrayList<>();

}


