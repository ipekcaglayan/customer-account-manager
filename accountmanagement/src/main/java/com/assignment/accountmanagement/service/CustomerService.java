package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.CreateCustomerDTO;
import com.assignment.accountmanagement.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO);

    List<CustomerDTO> getAllCustomers();

}
