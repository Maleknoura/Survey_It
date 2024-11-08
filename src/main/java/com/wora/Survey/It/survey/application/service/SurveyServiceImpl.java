package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.owner.domain.entity.Owner;
import com.wora.Survey.It.owner.domain.repository.OwnerRepository;
import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerDto;
import com.wora.Survey.It.survey.application.dto.response.ResponseDto;
import com.wora.Survey.It.survey.application.mapper.SurveyMapper;
import com.wora.Survey.It.survey.domain.entity.*;
import com.wora.Survey.It.survey.domain.enums.QuestionType;
import com.wora.Survey.It.survey.domain.repository.*;
import com.wora.Survey.It.survey.application.dto.request.SurveyRequestDto;
import com.wora.Survey.It.survey.application.dto.response.SurveyResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class SurveyServiceImpl implements GenericService<SurveyRequestDto, SurveyResponseDto, Long> {

    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;
    private final OwnerRepository ownerRepository;
    private final AnswerRepository answerRepository;
    private  final QuestionRepository questionRepository;


    @Override

    public SurveyResponseDto save(SurveyRequestDto surveyRequestDto) {
        Owner owner = ownerRepository.findById(surveyRequestDto.ownerId())
                .orElseThrow(() -> new EntityNotFoundException("Owner not found"));

        Survey survey = surveyMapper.toEntity(surveyRequestDto);
        survey.setOwner(owner);

        Survey savedSurvey = surveyRepository.save(survey);

        return surveyMapper.toDto(savedSurvey);
    }


    @Override
    public List<SurveyResponseDto> findAll() {
        return surveyRepository.findAll()
                .stream()
                .map(surveyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SurveyResponseDto> findById(Long id) {
        return surveyRepository.findById(id)
                .map(surveyMapper::toDto);
    }

    @Override
    @Transactional
    public SurveyResponseDto update(SurveyRequestDto surveyRequestDto, Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new RuntimeException("Survey not found with id: " + id);
        }

        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found with id: " + id));

        surveyMapper.updateEntityFromDto(surveyRequestDto, existingSurvey);

        Survey updatedSurvey = surveyRepository.save(existingSurvey);

        return surveyMapper.toDto(updatedSurvey);
    }

    @Transactional
    public boolean existsById(Long id) {
        return surveyRepository.existsById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!surveyRepository.existsById(id)) {
            throw new RuntimeException("Survey not found with id: " + id);
        }
        surveyRepository.deleteById(id);
    }

    @Transactional
    public void saveParticipation(Long surveyId, AnswerRequestDto participationDto) {

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new EntityNotFoundException("Survey with ID " + surveyId + " not found"));

        for (ResponseDto response : participationDto.responses()) {

            Question question = questionRepository.findById(response.questionId())
                    .orElseThrow(() -> new EntityNotFoundException("Question with ID " + response.questionId() + " not found"));

            if (question.getType() == QuestionType.SINGLE_CHOICE && response.answerId() != null) {
                Answer answer = answerRepository.findById(Long.valueOf(response.answerId()))
                        .orElseThrow(() -> new EntityNotFoundException("Answer with ID " + response.answerId() + " not found"));

                answer.incrementSelectionCount();
                answerRepository.save(answer);

            } else if (question.getType() == QuestionType.MULTIPLE_CHOICE && response.answers() != null) {
                for (AnswerDto answerDto : response.answers()) {
                    Answer answer = answerRepository.findById(Long.valueOf(answerDto.answerId()))
                            .orElseThrow(() -> new EntityNotFoundException("Answer with ID " + answerDto.answerId() + " not found"));

                    answer.incrementSelectionCount();
                    System.out.println("Selection Count after increment: " + answer.getSelectionCount());

                    answerRepository.save(answer);
                }
            }

            question.incrementAnswerCount();
            questionRepository.save(question);
        }

    }
}
