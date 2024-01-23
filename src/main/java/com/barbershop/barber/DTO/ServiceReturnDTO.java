package com.barbershop.barber.DTO;

import com.barbershop.barber.domain.customer.Customer;
import com.barbershop.barber.domain.servicebarber.EnumServiceBarber;
import com.barbershop.barber.domain.servicebarber.ServiceBarber;

import java.time.LocalDateTime;

public record ServiceReturnDTO(
        Long id,
        LocalDateTime date,

        EnumServiceBarber service,

        String nameCustomer
) {
    public ServiceReturnDTO(ServiceBarber data) {
        this(data.getId(), data.getDate(), data.getEnumServiceBarber(), data.getCustomer().getName());
    }

}
