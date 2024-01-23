package com.barbershop.barber.service;

import com.barbershop.barber.domain.servicebarber.ServiceBarber;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;

@Service
public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendMail(ServiceBarber data) {
        var name = data.getCustomer().getName();
        var email = data.getCustomer().getEmail();
        var service = data.getEnumServiceBarber().toString().toLowerCase();
        var date = data.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var time = data.getDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(name + " Thanks, for booking your service " + service+ ", with BarberTech in day: " + date + ", time: " + time);
        message.setTo(email);
        message.setFrom(sender);

        try {
            mailSender.send(message);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending email.";
        }
    }
}
