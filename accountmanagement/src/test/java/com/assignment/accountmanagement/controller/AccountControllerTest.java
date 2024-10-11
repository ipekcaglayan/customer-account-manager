package com.assignment.accountmanagement.controller;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CreateAccountDTO;
import com.assignment.accountmanagement.service.AccountService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
@Listeners(MockitoTestNGListener.class)

public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;


    @Test
    public void testCreateAccount() {
        CreateAccountDTO createAccountDTO = new CreateAccountDTO();
        AccountDTO accountDTO = new AccountDTO();

        when(accountService.createAccount(any(CreateAccountDTO.class))).thenReturn(accountDTO);

        ResponseEntity<AccountDTO> responseEntity = accountController.createAccount(createAccountDTO);

        assertNotNull(responseEntity);
        assertEquals(responseEntity.getStatusCodeValue(), 200);
        assertEquals(responseEntity.getBody(), accountDTO);
    }

}
