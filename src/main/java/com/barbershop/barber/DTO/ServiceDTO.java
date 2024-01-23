package com.barbershop.barber.DTO;

import com.barbershop.barber.domain.servicebarber.EnumServiceBarber;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceDTO(
        @NotNull
        EnumServiceBarber service,

        BigDecimal value,
        @NotNull
        LocalDateTime date,
        @NotNull
        Long idCustomer
) {
}
