package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.common.Validation.Exists;
import com.wora.Survey.It.survey.application.dto.request.SubSubjectRequestDto;
import com.wora.Survey.It.survey.application.dto.request.SubjectRequestdto;
import com.wora.Survey.It.survey.application.dto.response.SubSubjectResponseDto;
import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;

import com.wora.Survey.It.survey.application.mapper.SubjectMapper;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import com.wora.Survey.It.survey.domain.repository.SubjectRepository;
import com.wora.Survey.It.survey.domain.repository.SurveyEditionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements GenericService<SubjectRequestdto, SubjectResponseDto, Long> {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectMapper subjectMapper;

    @Autowired
    SurveyEditionRepository surveyEditionRepository;

    @Override
    public SubjectResponseDto save(SubjectRequestdto requestDto) {
        SurveyEdition surveyEdition = surveyEditionRepository.findById(requestDto.getSurveyEditionId())
                .orElseThrow(() -> new EntityNotFoundException("SurveyEdition not found with id: " + requestDto.getSurveyEditionId()));

        Subject subject = subjectMapper.toEntity(requestDto);
        subject.setSurveyEdition(surveyEdition);

        Subject savedSubject = subjectRepository.save(subject);
        return subjectMapper.toDto(savedSubject);
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

public SubSubjectResponseDto addSubSubject(Long parentSubjectId, SubSubjectRequestDto requestDto) {
    Subject parentSubject = subjectRepository.findById(parentSubjectId)
            .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

    Subject subSubject = subjectMapper.toSubSubjectEntity(requestDto);
    subSubject.setParentSubject(parentSubject);
    subSubject.setSurveyEdition(parentSubject.getSurveyEdition());

    Subject savedSubSubject = subjectRepository.save(subSubject);
    return subjectMapper.toSubSubjectDto(savedSubSubject);
}
}
