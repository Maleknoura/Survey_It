package com.wora.Survey.It.survey.application.mapper;

import com.wora.Survey.It.survey.application.dto.response.SubjectResponseDto;
import com.wora.Survey.It.survey.domain.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubSubjectMapper {

    @Mapping(source = "parentSubject.id", target = "parentSubjectId")
    SubjectResponseDto toSubjectResponseDto(Subject subSubject);
}
