package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.CreateCustomerDTO;

import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@Listeners(MockitoTestNGListener.class)
public class ApplicationReadyEventListenerTest {

    @InjectMocks
    private ApplicationReadyEventListener applicationReadyEventListener;

    @Mock
    private CustomerService customerService;

    @Test
    public void testHandleApplicationReady() {
        applicationReadyEventListener.handleApplicationReady();

        verify(customerService, times(1)).createCustomer(new CreateCustomerDTO("Ipek", "Caglayan"));
        verify(customerService, times(1)).createCustomer(new CreateCustomerDTO("Second", "Customer"));
    }
}
