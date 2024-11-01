package com.wora.Survey.It.owner.application.mapper;

import com.wora.Survey.It.owner.domain.entity.Owner;
import com.wora.Survey.It.owner.application.dto.OwnerRequestDTo;
import com.wora.Survey.It.owner.application.dto.OwnerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerRequestDTo ownerRequestDto);

    OwnerResponseDto toDto(Owner owner);

    void updateEntityFromDto(OwnerRequestDTo ownerRequestDto, @MappingTarget Owner existingOwner);
}
