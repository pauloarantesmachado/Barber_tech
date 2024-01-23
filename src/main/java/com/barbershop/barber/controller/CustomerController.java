package com.barbershop.barber.controller;

import com.barbershop.barber.DTO.CustomerDTO;
import com.barbershop.barber.DTO.CustomerUpdateDTO;
import com.barbershop.barber.domain.customer.Customer;
import com.barbershop.barber.DTO.CustomerDataDTO;
import com.barbershop.barber.domain.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO data, UriComponentsBuilder uriBuilder){
        var customer = new Customer(data);
        repository.save(customer);
        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDataDTO(customer));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDataDTO>> getListCustomer( @PageableDefault(size = 10, sort={"name"})Pageable paginator){
        var page = repository.findAllByStatusTrue(paginator).map(CustomerDataDTO::new);
        return ResponseEntity.ok(page);
    }
     @PutMapping
     @Transactional
     public ResponseEntity updateCustomer(@RequestBody @Valid CustomerUpdateDTO data) {
         var customer = repository.getReferenceById(data.id());
         customer.updateCustomer(data);
         return ResponseEntity.ok(new CustomerUpdateDTO(customer));
     }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var customer = repository.getReferenceById(id);
        customer.deleteCustomer(customer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Long id){
        var customer = repository.getReferenceById(id);
        return ResponseEntity.ok(new CustomerDataDTO(customer));
    }
 }