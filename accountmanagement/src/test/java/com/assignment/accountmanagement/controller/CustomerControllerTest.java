package com.assignment.accountmanagement.controller;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CustomerDTO;
import com.assignment.accountmanagement.service.AccountService;
import com.assignment.accountmanagement.service.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Listeners(MockitoTestNGListener.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private AccountService accountService;

    @Mock
    private CustomerService customerService;


    @Test
    public void testGetAllCustomers() {
        List<CustomerDTO> mockCustomers = new ArrayList<>();
        mockCustomers.add(new CustomerDTO());
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        ResponseEntity<List<CustomerDTO>> response = customerController.getAllCustomers();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockCustomers);
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    public void testGetAllCustomerAccounts() {
        Long customerId = 1L;
        List<AccountDTO> mockAccounts = new ArrayList<>();
        mockAccounts.add(new AccountDTO(/* parameters */)); // Use actual parameters
        when(accountService.getAccountsByCustomerId(customerId)).thenReturn(mockAccounts);

        ResponseEntity<List<AccountDTO>> response = customerController.getAllCustomerAccounts(customerId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockAccounts);
        verify(accountService, times(1)).getAccountsByCustomerId(customerId);
    }

}
