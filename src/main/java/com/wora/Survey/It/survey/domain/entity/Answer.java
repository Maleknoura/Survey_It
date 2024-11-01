package com.wora.Survey.It.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private int selectionCount;


    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}

