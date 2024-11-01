package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.domain.entity.Subject;
import com.wora.Survey.It.survey.application.dto.request.SubjectRequestdto;
import com.wora.Survey.It.survey.application.dto.request.SubSubjectRequestDto;
import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;
import com.wora.Survey.It.survey.application.dto.response.SubSubjectResponseDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface SubjectMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subSubjects", ignore = true)
    @Mapping(target = "surveyEdition", ignore = true)
    @Mapping(target = "parentSubject", ignore = true)
    Subject toEntity(SubjectRequestdto requestDto);

    SubjectResponseDto toDto(Subject subject);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subSubjects", ignore = true)
    @Mapping(target = "surveyEdition", ignore = true)
    @Mapping(target = "parentSubject", ignore = true)
    Subject toSubSubjectEntity(SubSubjectRequestDto requestDto);

    @Mapping(source = "parentSubject.id", target = "parentSubjectId")
    SubSubjectResponseDto toSubSubjectDto(Subject subject);

    @AfterMapping
    default void linkSubSubjects(@MappingTarget Subject subject) {
        if (subject.getSubSubjects() != null) {
            subject.getSubSubjects().forEach(subSubject -> {
                subSubject.setParentSubject(subject);
            });
        }
    }
}