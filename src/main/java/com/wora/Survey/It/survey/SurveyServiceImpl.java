package com.wora.Survey.It.survey;

import com.wora.Survey.It.answer.Answer;
import com.wora.Survey.It.common.GenericService;
import com.wora.Survey.It.owner.Owner;
import com.wora.Survey.It.owner.OwnerRepository;
import com.wora.Survey.It.survey.dto.SurveyRequestDto;
import com.wora.Survey.It.survey.dto.SurveyResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
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
    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyMapper surveyMapper,OwnerRepository ownerRepository){
        this.surveyRepository=surveyRepository;
        this.surveyMapper=surveyMapper;
        this.ownerRepository=ownerRepository;
        System.out.println("this service is initialzed");
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
