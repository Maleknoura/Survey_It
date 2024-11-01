package com.wora.Survey.It.owner.domain.entity;

import com.wora.Survey.It.survey.domain.entity.Survey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Survey> surveys;

}

