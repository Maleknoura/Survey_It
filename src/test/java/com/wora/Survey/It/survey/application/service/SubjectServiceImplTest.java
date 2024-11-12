package com.wora.Survey.It.survey.application.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.wora.Survey.It.survey.application.dto.nested.SubjectNestedDto;
import com.wora.Survey.It.survey.application.dto.nested.SurveyEdNestedDto;
import com.wora.Survey.It.survey.application.dto.request.SubjectRequestdto;
import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;
import com.wora.Survey.It.survey.application.mapper.SubjectMapper;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import com.wora.Survey.It.survey.domain.repository.SubjectRepository;
import com.wora.Survey.It.survey.domain.repository.SurveyEditionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private SubjectMapper subjectMapper;

    @Mock
    private SurveyEditionRepository surveyEditionRepository;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Test
    public void save_ShouldReturnSubjectResponseDto_WhenValidRequest() {

        SubjectRequestdto requestDto = new SubjectRequestdto("title", 2L, 1L);
        SurveyEdition surveyEdition = new SurveyEdition(1L, "Survey Edition");
        Subject subject = new Subject(1L, "Subject Name", surveyEdition);

        SurveyEdNestedDto surveyEdNestedDto = new SurveyEdNestedDto(
                1L,
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 30),
                LocalDate.now(),
                5,
                100L
        );        List<SubjectNestedDto> nestedSubjects = List.of();

        SubjectResponseDto subjectResponseDto = new SubjectResponseDto(1L, "Subject Name", 1L, surveyEdNestedDto, nestedSubjects);

        when(surveyEditionRepository.findById(requestDto.getSurveyEditionId())).thenReturn(Optional.of(surveyEdition));
        when(subjectMapper.toEntity(requestDto)).thenReturn(subject);
        when(subjectRepository.save(subject)).thenReturn(subject);
        when(subjectMapper.toDto(subject)).thenReturn(subjectResponseDto);


        SubjectResponseDto result = subjectService.save(requestDto);


        assertNotNull(result);
        assertEquals(subjectResponseDto.title(), result.title());
        verify(subjectRepository).save(subject);
    }

    @Test
    public void save_ShouldThrowException_WhenSurveyEditionNotFound() {

        SubjectRequestdto requestDto = new SubjectRequestdto("title", 4L, 1L);
        when(surveyEditionRepository.findById(requestDto.getSurveyEditionId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> subjectService.save(requestDto));
    }
}
