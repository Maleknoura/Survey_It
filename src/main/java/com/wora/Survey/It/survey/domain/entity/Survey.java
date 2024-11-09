package com.wora.Survey.It.survey.domain.entity;

import com.wora.Survey.It.owner.domain.entity.Owner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String description;
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;


    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<SurveyEdition> editions;


    public List<SurveyEdition> getSurveyEditions() {
        return editions;
    }
}

