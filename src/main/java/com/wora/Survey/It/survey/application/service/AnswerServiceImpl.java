package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.survey.application.dto.request.AnswerRequestDto;
import com.wora.Survey.It.survey.application.dto.request.CreateAnswerDto;
import com.wora.Survey.It.survey.application.dto.response.AnswerResponseDto;
import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.survey.application.dto.response.QuestionResponseDto;
import com.wora.Survey.It.survey.application.mapper.AnswerMapper;
import com.wora.Survey.It.survey.domain.entity.Answer;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.repository.AnswerRepository;
import com.wora.Survey.It.survey.domain.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class AnswerServiceImpl implements GenericService<CreateAnswerDto, AnswerResponseDto, Long> {

    @Autowired
    private AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    @Autowired
    private QuestionServiceImpl questionServiceImpl;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImpl(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public AnswerResponseDto save(CreateAnswerDto answerRequestDto) {
        Long questionId = answerRequestDto.questionId();

        Optional<Question> question = questionRepository.findById(questionId);

        if (question.isEmpty()) {
            throw new EntityNotFoundException("Question with id " + questionId + " not found");
        }

        Answer answer = answerMapper.toEntity(answerRequestDto);

        answer.setQuestion(question.get());

        try {
            Answer savedAnswer = answerRepository.save(answer);
            return answerMapper.toAnswerResponseDto(savedAnswer);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Error saving the answer: integrity violation", e);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error occurred while saving the answer", e);
        }
    }



    @Override
    public List<AnswerResponseDto> findAll() {
        try {
            List<Answer> answers = answerRepository.findAll();
            return answers.stream()
                    .map(answerMapper::toAnswerResponseDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<AnswerResponseDto> findById(Long id) {
        return answerRepository.findById(id)
                .map(answerMapper::toAnswerResponseDto);
    }

    @Override
    public AnswerResponseDto update(CreateAnswerDto answerRequestDto, Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isEmpty()) {
            throw new EntityNotFoundException("Answer not found with id: " + id);
        }

        Answer existingAnswer = optionalAnswer.get();

        answerMapper.updateEntityFromDto(answerRequestDto, existingAnswer);
        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return answerMapper.toAnswerResponseDto(updatedAnswer);
    }

    @Override
    public void deleteById(Long id) {
        if (answerRepository.existsById(id)) {
            answerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Answer not found with id: " + id);
        }
    }
}
