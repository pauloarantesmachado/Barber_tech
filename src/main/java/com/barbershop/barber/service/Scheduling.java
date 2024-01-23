package com.barbershop.barber.service;

import com.barbershop.barber.DTO.ServiceDTO;
import com.barbershop.barber.validators.ValidatorSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Scheduling {
    @Autowired
    private List<ValidatorSchedule> validatorSchedules;
    public void schedule(ServiceDTO data){
        validatorSchedules.forEach(v -> v.validator(data));
    }
}
