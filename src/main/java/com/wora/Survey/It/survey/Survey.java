package com.wora.Survey.It.survey;

import com.wora.Survey.It.owner.Owner;
import com.wora.Survey.It.surveyEdition.SurveyEdition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;


    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyEdition> editions;



}

