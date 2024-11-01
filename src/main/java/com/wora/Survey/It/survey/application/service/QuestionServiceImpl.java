package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.survey.application.dto.request.QuestionRequestDto;
import com.wora.Survey.It.survey.application.dto.response.QuestionResponseDto;
import com.wora.Survey.It.survey.application.mapper.QuestionMapper;
import com.wora.Survey.It.survey.domain.entity.Question;
import com.wora.Survey.It.survey.domain.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements GenericService<QuestionRequestDto, QuestionResponseDto, Long> {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public QuestionResponseDto save(QuestionRequestDto questionRequestDto) {
        Question question = questionMapper.toEntity(questionRequestDto);
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
