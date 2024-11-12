package com.wora.Survey.It.survey.domain.entity;


import com.wora.Survey.It.survey.domain.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    @Enumerated(EnumType.ORDINAL)
    private QuestionType type;
    private int answerCount;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Question(long l, String simpleQuestion) {

    }

    public Question(long l, String testQuestion, Subject subject) {

    }


    public void incrementAnswerCount() {
        this.answerCount++;

    }
}

