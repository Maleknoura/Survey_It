package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.survey.application.dto.nested.NestedQuestionDto;
import com.wora.Survey.It.survey.application.dto.request.QuestionRequestDto;
import com.wora.Survey.It.survey.application.dto.response.QuestionResponseDto;
import com.wora.Survey.It.survey.application.mapper.QuestionMapper;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.enums.QuestionType;
import com.wora.Survey.It.survey.domain.repository.QuestionRepository;
import com.wora.Survey.It.survey.domain.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionMapper questionMapper;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private Subject subject;
    private Question question;
    private QuestionRequestDto questionRequestDto;
    private QuestionResponseDto questionResponseDto;
    private NestedQuestionDto nestedQuestionDto;

    @BeforeEach
    void setUp() {
        subject = new Subject(1L, "Test Subject");
        question = new Question(1L, "Test Question", subject);
        questionRequestDto = new QuestionRequestDto("Test Question", QuestionType.MULTIPLE_CHOICE, 1L);
        nestedQuestionDto = new NestedQuestionDto(1L, "Test Nested Question");
    }


    @Test
    public void save_ShouldThrowEntityNotFoundException_WhenSubjectDoesNotExist() {

        when(subjectRepository.findById(questionRequestDto.subjectId())).thenReturn(Optional.empty());


        assertThrows(EntityNotFoundException.class, () -> questionService.save(questionRequestDto));
        verify(questionRepository, never()).save(any(Question.class));
    }


    @Test
    public void findAll_ShouldThrowIllegalStateException_WhenExceptionOccurs() {

        when(questionRepository.findAll()).thenThrow(new RuntimeException("Database error"));


        assertThrows(IllegalStateException.class, () -> questionService.findAll());
    }



    @Test
    public void findById_ShouldReturnEmptyOptional_WhenQuestionDoesNotExist() {

        when(questionRepository.findById(1L)).thenReturn(Optional.empty());


        Optional<QuestionResponseDto> result = questionService.findById(1L);


        assertTrue(result.isEmpty());
        verify(questionRepository).findById(1L);
    }
}
