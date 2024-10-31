package com.wora.Survey.It.subject;

import ch.qos.logback.core.model.ComponentModel;
import com.wora.Survey.It.subject.dto.SubjectRequestdto;
import com.wora.Survey.It.subject.dto.SubjectResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")

public interface SubjectMapper {
    SubjectMapper Instance = Mappers.getMapper(SubjectMapper.class);

    Subject toEntity(SubjectRequestdto dto);

    SubjectResponseDto toDto(Subject Entity);

    void updateEntityFromDto(SubjectRequestdto dto, @MappingTarget Subject existingSubject);
}
