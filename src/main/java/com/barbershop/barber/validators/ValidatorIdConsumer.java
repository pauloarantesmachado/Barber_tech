package com.barbershop.barber.validators;

import com.barbershop.barber.DTO.ServiceDTO;
import com.barbershop.barber.domain.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorIdConsumer implements  ValidatorSchedule{
    @Autowired
    private CustomerRepository repository;

    public void validator(ServiceDTO data){
        if(!repository.existsById(data.idCustomer())){
            throw new RuntimeException("Customer not exist");
        }
    }


}
