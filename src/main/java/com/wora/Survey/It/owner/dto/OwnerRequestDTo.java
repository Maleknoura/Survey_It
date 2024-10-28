package com.wora.Survey.It.owner.dto;
import jakarta.validation.constraints.NotBlank;
public record OwnerRequestDTo(
        @NotBlank(message = "Owner name is required") String name
) {
}
