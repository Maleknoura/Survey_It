package com.wora.Survey.It.owner.dto;
import com.wora.Survey.It.common.Validation.Exists;
import com.wora.Survey.It.owner.Owner;
import jakarta.validation.constraints.NotBlank;
public record OwnerRequestDTo(
        @NotBlank(message = "Owner name is required")
        @Exists(entity = Owner.class, field = "title", message = "Le nom du Owner du survey doit être unique")
        String name
) {
}
