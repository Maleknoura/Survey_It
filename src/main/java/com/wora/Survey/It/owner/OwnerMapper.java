package com.wora.Survey.It.owner;

import com.wora.Survey.It.owner.dto.OwnerRequestDTo;
import com.wora.Survey.It.owner.dto.OwnerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toEntity(OwnerRequestDTo ownerRequestDto);

    OwnerResponseDto toDto(Owner owner);

    void updateEntityFromDto(OwnerRequestDTo ownerRequestDto, @MappingTarget Owner existingOwner);
}
