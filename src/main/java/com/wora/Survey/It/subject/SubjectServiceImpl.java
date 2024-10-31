package com.wora.Survey.It.subject;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.question.dto.QuestionRequestDto;
import com.wora.Survey.It.subject.dto.SubjectRequestdto;
import com.wora.Survey.It.subject.dto.SubjectResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements GenericService<SubjectRequestdto, SubjectResponseDto, Long> {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public SubjectResponseDto save(SubjectRequestdto subjectRequestdto) {
        return null;
    }

    @Override
    public List<SubjectResponseDto> findAll() {
        return List.of();
    }

    @Override
    public Optional<SubjectResponseDto> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public SubjectResponseDto update(SubjectRequestdto subjectRequestdto, Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
