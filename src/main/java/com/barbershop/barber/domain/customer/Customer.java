package com.barbershop.barber.domain.customer;

import com.barbershop.barber.DTO.CustomerDTO;
import com.barbershop.barber.DTO.CustomerUpdateDTO;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "customer")
@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    private Boolean status;

    public Customer(CustomerDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.cpf = data.cpf();
        this.status = true;
    }

    public void updateCustomer(CustomerUpdateDTO data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.phone() !=null){
            this.phone = data.phone();
        }
        if(data.email() != null){
            this.email = data.email();
        }

    }

    public void deleteCustomer(Customer customer){
        if(customer.getStatus()){
            this.status = false;
        }
    }


}
