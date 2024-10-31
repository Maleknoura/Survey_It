package com.wora.Survey.It.answer;

import com.wora.Survey.It.answer.dto.AnswerRequestDto;
import com.wora.Survey.It.answer.dto.AnswerResponseDto;
import com.wora.Survey.It.common.GenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.Hibernate.map;

@Service
@Validated
public class AnswerServiceImpl implements GenericService<AnswerRequestDto, AnswerResponseDto, Long> {
    @Autowired
   private AnswerRepository answerRepository;

   private final AnswerMapper answerMapper;
    @Autowired
    public AnswerServiceImpl(AnswerMapper answerMapper) {
        this.answerMapper = answerMapper;
    }

    @Override
    public AnswerResponseDto save(AnswerRequestDto answerRequestDto) {
        Answer answer = answerMapper.toEntity(answerRequestDto);
        Answer savedAnswer = answerRepository.save(answer);
        return answerMapper.toDto(savedAnswer);
    }


    @Override
    public List<AnswerResponseDto> findAll() {
        try {
            List<Answer> answers = answerRepository.findAll();
            return answers.stream()
                    .map(answerMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<AnswerResponseDto> findById(Long id) {
        return answerRepository.findById(id)
                .map(answerMapper::toDto);
    }

    @Override
    public AnswerResponseDto update(AnswerRequestDto answerRequestDto, Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isEmpty()) {
            return null;
        }

        Answer existingAnswer = optionalAnswer.get();

        answerMapper.updateEntityFromDto(answerRequestDto, existingAnswer);
        Answer updatedAnswer = answerRepository.save(existingAnswer);
        return answerMapper.toDto(updatedAnswer);
    }


    @Override
    public void deleteById(Long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);

        if (optionalAnswer.isPresent()) {
            answerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Answer not found with id: " + id);
        }
    }

}
