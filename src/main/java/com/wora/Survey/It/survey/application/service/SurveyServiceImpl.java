package com.wora.Survey.It.survey.application.service;

import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.owner.domain.entity.Owner;
import com.wora.Survey.It.owner.domain.repository.OwnerRepository;
import com.wora.Survey.It.survey.application.mapper.SurveyMapper;
import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.domain.entity.SurveyEdition;
import com.wora.Survey.It.survey.domain.repository.SubjectRepository;
import com.wora.Survey.It.survey.domain.repository.SurveyEditionRepository;
import com.wora.Survey.It.survey.domain.repository.SurveyRepository;
import com.wora.Survey.It.survey.application.dto.request.SurveyRequestDto;
import com.wora.Survey.It.survey.application.dto.response.SurveyResponseDto;
import com.wora.Survey.It.survey.domain.entity.Survey;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
public class SurveyServiceImpl implements GenericService<SurveyRequestDto, SurveyResponseDto, Long> {

   private final SurveyRepository surveyRepository;
   private final SurveyMapper surveyMapper;
   private final OwnerRepository ownerRepository;



    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyMapper surveyMapper, OwnerRepository ownerRepository)
                             {
        this.surveyRepository=surveyRepository;
        this.surveyMapper=surveyMapper;
        this.ownerRepository=ownerRepository;

    }

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


}
