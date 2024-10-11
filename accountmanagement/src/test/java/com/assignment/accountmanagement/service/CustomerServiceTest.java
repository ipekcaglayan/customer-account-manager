package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.CreateCustomerDTO;
import com.assignment.accountmanagement.mapper.CustomerMapper;
import com.assignment.accountmanagement.model.Customer;
import com.assignment.accountmanagement.repository.CustomerRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.Mockito.verify;


@Listeners(MockitoTestNGListener.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    public void testCreateCustomer() {
        String firstName = "firstName";
        String lastName = "lastName";
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(firstName, lastName);
        customerService.createCustomer(createCustomerDTO);

        verify(customerRepository).save(new Customer(firstName, lastName));
    }

    @Test

    public void testGetAllCustomers() {
        customerService.getAllCustomers();

        verify(customerRepository).findAll();
    }
}
