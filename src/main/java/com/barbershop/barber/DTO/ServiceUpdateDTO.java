package com.barbershop.barber.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ServiceUpdateDTO(
        @NotNull
        Long id,

        @NotNull
        LocalDateTime date
) {
}
