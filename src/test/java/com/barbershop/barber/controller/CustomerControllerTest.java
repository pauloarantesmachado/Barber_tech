package com.barbershop.barber.controller;

import com.barbershop.barber.DTO.CustomerDTO;
import com.barbershop.barber.DTO.CustomerUpdateDTO;
import com.barbershop.barber.domain.customer.Customer;
import com.barbershop.barber.domain.customer.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository repository;

    @Autowired
    private JacksonTester<CustomerDTO> jsonDto;

    @Autowired
    private JacksonTester<CustomerUpdateDTO> jsonDtoUpdate;

    @Mock
    private Customer customer;

    @DisplayName("should return code http 201 when a data it's valid")
    @Test
    void registerCustomer200() throws Exception{
        CustomerDTO dto = new CustomerDTO("Paulo", "12977654533", "paulo@test.com", "33333333333");
        var response = mvc.perform(
                post("/customer")
                        .content(jsonDto.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();
        assertEquals(201, response.getStatus());

    }

    @DisplayName("should return code http 400 when a data it's invalid")
    @Test
    void registerCustomer400() throws Exception{
        String json = "{}";
        var response = mvc.perform(
                post("/customer")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(400, response.getStatus());
    }

    @DisplayName("should return code http 200 when a data it's valid to update customer")
    @Test
    void updateCustomer200() throws Exception{
        CustomerUpdateDTO dto = new CustomerUpdateDTO(1l,"Paulo", "paulo@test.com", "12345678766");
        given(repository.getReferenceById(dto.id())).willReturn(this.customer);
        this.customer.updateCustomer(dto);
        var response = mvc.perform(
                put("/customer")
                        .content(jsonDtoUpdate.write(dto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());

    }

    @DisplayName("should return code http 200 when a data it's null to update customer")
    @Test
    void updateCustomer400() throws Exception{
        String json = "{}";
        given(repository.getReferenceById(any())).willReturn(null);
        this.customer.updateCustomer(any());
        var response = mvc.perform(
                put("/customer")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(400, response.getStatus());
    }

    @DisplayName("should return code http 200 when a data it's valid to delete customer")
    @Test
    void deleteCustomer200() throws Exception{
        Long idCustomer = 1l;
        given(repository.getReferenceById(idCustomer)).willReturn(this.customer);
        this.customer.deleteCustomer(this.customer);
        var response = mvc.perform(
                delete("/customer/"+idCustomer)
                        .content(String.valueOf(idCustomer))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(204, response.getStatus());
    }


}