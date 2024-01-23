package com.barbershop.barber.DTO;

import com.barbershop.barber.domain.customer.Customer;

public record CustomerDataDTO(
        Long id,
        String name,
        String email
) {
    public CustomerDataDTO(Customer customer){
        this(customer.getId(), customer.getName(), customer.getEmail());
    }
}
