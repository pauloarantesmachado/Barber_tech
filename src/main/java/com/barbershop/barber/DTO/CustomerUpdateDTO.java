package com.barbershop.barber.DTO;

import com.barbershop.barber.domain.customer.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerUpdateDTO(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @Email
        String email,
        @NotNull
        String phone
) {
    public CustomerUpdateDTO(Customer data) {
        this(data.getId(), data.getName(), data.getEmail(), data.getPhone());
    }
}
