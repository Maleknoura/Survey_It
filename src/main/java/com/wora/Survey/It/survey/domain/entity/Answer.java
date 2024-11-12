package com.wora.Survey.It.survey.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private Double percentage;
    private int selectionCount;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Answer(long l, String sampleAnswer, int i) {
    }

    public void calculatePercentage(int totalAnswers) {
        if (totalAnswers > 0) {
            this.percentage = (double) selectionCount / totalAnswers * 100;
        }
    }

    public void incrementSelectionCount() {
        this.selectionCount++;
        calculatePercentage();

    }
    private void calculatePercentage() {
        if (question.getAnswerCount() > 0) {
            this.percentage = ((double) this.selectionCount / question.getAnswerCount()) * 100;
        } else {
            this.percentage = 0.0;
        }
    }
}

