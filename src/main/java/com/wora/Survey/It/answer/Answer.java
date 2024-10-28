package com.wora.Survey.It.answer;

import com.wora.Survey.It.question.Question;
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

