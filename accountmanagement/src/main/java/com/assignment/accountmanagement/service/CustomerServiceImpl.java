package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.CreateCustomerDTO;
import com.assignment.accountmanagement.dto.CustomerDTO;
import com.assignment.accountmanagement.mapper.CustomerMapper;
import com.assignment.accountmanagement.model.Customer;
import com.assignment.accountmanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        String firstName = createCustomerDTO.getFirstName();
        String lastName = createCustomerDTO.getLastName();
        Customer customer = customerRepository.save(new Customer(firstName, lastName));
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.toDto(customerRepository.findAll());
    }
}
