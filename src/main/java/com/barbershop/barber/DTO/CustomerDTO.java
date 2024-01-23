package com.barbershop.barber.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDTO(

        @NotBlank
        String name,

        String phone,
        @NotNull
        @Email
        String email,
        @NotNull
        String cpf
) {
}
