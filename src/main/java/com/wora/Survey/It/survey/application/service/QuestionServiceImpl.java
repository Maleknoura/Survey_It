package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.survey.application.dto.request.QuestionRequestDto;
import com.wora.Survey.It.survey.application.dto.response.QuestionResponseDto;
import com.wora.Survey.It.survey.application.mapper.QuestionMapper;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.repository.QuestionRepository;
import com.wora.Survey.It.survey.domain.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements GenericService<QuestionRequestDto, QuestionResponseDto, Long> {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final SubjectRepository subjectRepository;

    public QuestionServiceImpl(
            QuestionRepository questionRepository,
            QuestionMapper questionMapper,
            SubjectRepository subjectRepository
    ) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.subjectRepository = subjectRepository;
    }


    @Override
    public QuestionResponseDto save(QuestionRequestDto questionRequestDto) {
        Subject subject = subjectRepository.findById(questionRequestDto.subjectId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Subject not found with id: " + questionRequestDto.subjectId()));

        Question question = questionMapper.toEntity(questionRequestDto);

        question.setSubject(subject);

        Question savedQuestion = questionRepository.save(question);

        return questionMapper.toDto(savedQuestion);
    }

    @Override
    public List<QuestionResponseDto> findAll() {
        try {
            List<Question> questions = questionRepository.findAll();
            return questions.stream()
                    .map(questionMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("An error occurred while retrieving questions", e);
        }
    }

    @Override
    public Optional<QuestionResponseDto> findById(Long id) {
        return questionRepository.findById(id)
                .map(questionMapper::toDto);
    }

    @Override
    public QuestionResponseDto update(QuestionRequestDto questionRequestDto, Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
