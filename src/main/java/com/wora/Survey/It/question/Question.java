package com.wora.Survey.It.question;


import com.wora.Survey.It.answer.Answer;
import com.wora.Survey.It.subject.Subject;
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

    @Enumerated(EnumType.STRING)
    private QuestionType type;
    private int answerCount;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;
}

