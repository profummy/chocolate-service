package com.profummy.chocolateservice.services;

import com.profummy.chocolateservice.web.model.ChocolateDto;
import com.profummy.chocolateservice.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomerById(UUID customerId);
    CustomerDto saveNewCustomer(CustomerDto customerDto);
    void updateCustomer(UUID customerId,CustomerDto customerDto);
    void deleteById(UUID customerId);

}

