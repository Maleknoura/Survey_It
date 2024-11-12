package com.wora.Survey.It.survey.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.wora.Survey.It.owner.domain.entity.Owner;
import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.request.SurveyRequestDto;
import com.wora.Survey.It.survey.application.dto.response.SurveyResponseDto;
import com.wora.Survey.It.survey.application.mapper.SurveyMapper;
import com.wora.Survey.It.survey.domain.entity.Survey;
import com.wora.Survey.It.survey.domain.repository.SurveyRepository;
import com.wora.Survey.It.owner.domain.repository.OwnerRepository;
import com.wora.Survey.It.survey.application.dto.response.ResponseDto;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Answer;
import com.wora.Survey.It.survey.domain.repository.QuestionRepository;
import com.wora.Survey.It.survey.domain.repository.AnswerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;

public class SurveyServiceImplTest {

    @InjectMocks
    private SurveyServiceImpl surveyService;

    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private SurveyMapper surveyMapper;
    @Mock
    private OwnerRepository ownerRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QuestionRepository questionRepository;

    private SurveyRequestDto surveyRequestDto;
    private SurveyResponseDto surveyResponseDto;
    private Survey survey;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        surveyRequestDto = new SurveyRequestDto("title", "description", true, 1L);
        surveyResponseDto = new SurveyResponseDto(1L, "titre", "une description",false);
        survey = new Survey(1L, "surveytitle", 1L);
    }

    @Test
    public void save_Success() {
        Owner owner = new Owner(1L, "Owner Name");
        when(ownerRepository.findById(surveyRequestDto.ownerId())).thenReturn(Optional.of(owner));
        when(surveyMapper.toEntity(surveyRequestDto)).thenReturn(survey);
        when(surveyRepository.save(survey)).thenReturn(survey);
        when(surveyMapper.toDto(survey)).thenReturn(surveyResponseDto);

        SurveyResponseDto result = surveyService.save(surveyRequestDto);


        assertNotNull(result);
        assertEquals(surveyResponseDto.id(), result.id());
        verify(ownerRepository).findById(surveyRequestDto.ownerId());
        verify(surveyRepository).save(survey);
    }

    @Test
    public void save_EntityNotFoundException_WhenOwnerNotFound() {
        when(ownerRepository.findById(surveyRequestDto.ownerId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> surveyService.save(surveyRequestDto));
    }

    @Test
    public void findAll_Success() {
        when(surveyRepository.findAll()).thenReturn(List.of(survey));
        when(surveyMapper.toDto(survey)).thenReturn(surveyResponseDto);

        var result = surveyService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(surveyResponseDto.id(), result.get(0).id());
    }

    @Test
    public void findById_Success() {
        when(surveyRepository.findById(1L)).thenReturn(Optional.of(survey));
        when(surveyMapper.toDto(survey)).thenReturn(surveyResponseDto);

        var result = surveyService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(surveyResponseDto.id(), result.get().id());
    }

    @Test
    public void update_RuntimeException_WhenSurveyNotFound() {
        when(surveyRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> surveyService.update(surveyRequestDto, 1L));
    }

    @Test
    public void deleteById_Success() {
        when(surveyRepository.existsById(1L)).thenReturn(true);

        surveyService.deleteById(1L);

        verify(surveyRepository).deleteById(1L);
    }

    @Test
    public void deleteById_RuntimeException_WhenSurveyNotFound() {
        when(surveyRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> surveyService.deleteById(1L));
    }


    @Test
    public void saveParticipation_EntityNotFoundException_WhenSurveyNotFound() {
        when(surveyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> surveyService.saveParticipation(1L, mock(AnswerRequestDto.class)));
    }
}
