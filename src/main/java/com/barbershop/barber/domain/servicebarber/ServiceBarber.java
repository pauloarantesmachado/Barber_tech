package com.barbershop.barber.domain.servicebarber;

import com.barbershop.barber.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "service")
@Getter
@NoArgsConstructor
public class ServiceBarber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EnumServiceBarber enumServiceBarber;

    private BigDecimal value;

    private LocalDateTime date;

    @ManyToOne
    private Customer customer;

    public ServiceBarber(EnumServiceBarber enumServiceBarber, BigDecimal value, LocalDateTime date, Customer customer) {
        this.enumServiceBarber = enumServiceBarber;
        this.value = value;
        this.date = date;
        this.customer = customer;
    }

    public void updateService(LocalDateTime date){
        this.date = date;
    }
}
