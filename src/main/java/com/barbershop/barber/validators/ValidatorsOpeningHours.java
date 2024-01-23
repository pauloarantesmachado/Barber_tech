package com.barbershop.barber.validators;

import com.barbershop.barber.DTO.ServiceDTO;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class ValidatorsOpeningHours  implements ValidatorSchedule{
    public void validator(ServiceDTO data){
        var dateSchedule = data.date();
        var sunday = dateSchedule.getDayOfWeek().equals(DayOfWeek.SATURDAY);
        var beforeOpen = dateSchedule.getHour() < 7;
        var afterClinicClose = dateSchedule.getHour() > 20;

        if(sunday || beforeOpen || afterClinicClose){
            throw new ValidationException("Barbershop after hours ");
        }

    }
}
