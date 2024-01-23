package com.barbershop.barber.controller;

import com.barbershop.barber.DTO.*;
import com.barbershop.barber.domain.customer.CustomerRepository;
import com.barbershop.barber.domain.servicebarber.ServiceBarber;
import com.barbershop.barber.domain.servicebarber.ServiceRepository;
import com.barbershop.barber.service.Scheduling;
import com.barbershop.barber.service.SendEmail;
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
@RequestMapping("/service")
public class ServiceBarberController {
    @Autowired
    private CustomerRepository repository;

    @Autowired

    private ServiceRepository serviceRepository;

    @Autowired
    private Scheduling scheduling;

    @Autowired
    private SendEmail email;

    @PostMapping
    @Transactional
    public ResponseEntity registerService(@RequestBody @Valid ServiceDTO data, UriComponentsBuilder uriBuilder){
        scheduling.schedule(data);
        var customer = repository.findById(data.idCustomer());
        var service = new ServiceBarber(data.service(), data.value(), data.date(), customer.get());
        serviceRepository.save(service);
        email.sendMail(service);
        var uri = uriBuilder.path("/customer/{id}").buildAndExpand(service.getId()).toUri();
        return ResponseEntity.created(uri).body(new ServiceReturnDTO(service));
    }


    @GetMapping
    public ResponseEntity<Page<ServiceReturnDTO>> getListService(@PageableDefault(size = 10, sort={"date"}) Pageable paginator){
        var page = serviceRepository.findAll(paginator).map(ServiceReturnDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateCustomer(@RequestBody @Valid ServiceUpdateDTO data) {
        var service = serviceRepository.getReferenceById(data.id());
        service.updateService(data.date());
        return ResponseEntity.ok(new ServiceReturnDTO(service));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var service = serviceRepository.getReferenceById(id);
        serviceRepository.delete(service);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Long id){
        var service = serviceRepository.getReferenceById(id);
        return ResponseEntity.ok(new ServiceReturnDTO(service));
    }

}
