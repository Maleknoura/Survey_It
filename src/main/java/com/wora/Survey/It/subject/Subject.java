package com.wora.Survey.It.subject;


import com.wora.Survey.It.surveyEdition.SurveyEdition;
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
    @JoinColumn(name = "survey_edition_id")
    private SurveyEdition surveyEdition;

    @OneToMany(mappedBy = "parentSubject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subSubjects = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_subject_id")
    private Subject parentSubject;

}


