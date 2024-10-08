package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CreateAccountDTO;
import com.assignment.accountmanagement.dto.CustomerDTO;
import com.assignment.accountmanagement.exception.CustomerNotFoundException;
import com.assignment.accountmanagement.mapper.AccountMapper;
import com.assignment.accountmanagement.messaging.NotificationContentDTO;
import com.assignment.accountmanagement.messaging.NotificationContentType;
import com.assignment.accountmanagement.messaging.NotificationProducer;
import com.assignment.accountmanagement.model.Account;
import com.assignment.accountmanagement.model.Customer;
import com.assignment.accountmanagement.repository.AccountRepository;
import com.assignment.accountmanagement.repository.CustomerRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AccountMapper accountMapper;
    @Captor
    ArgumentCaptor<NotificationContentDTO> notificationContentDTOArgumentCaptor;
    @Mock
    NotificationProducer notificationProducer;
    private Long customerId;
    private CreateAccountDTO createAccountDTO;
    private Customer customer;

    @BeforeMethod
    public void setUp() {
        customerId = new Random().nextLong();
        createAccountDTO = new CreateAccountDTO(customerId, 0L);
        customer = new Customer();
        customer.setId(customerId);
    }

    @Test
    public void testCreateAccount_WithInitialCreditZero_SuccessfulCreation() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        accountService.createAccount(createAccountDTO);

        verify(accountRepository).save(new Account(customer));
    }

    @Test
    public void testCreateAccount_WithInitialCreditGreaterThanZero_SuccessfulCreation() {
        createAccountDTO.setInitialCredit(10L);
        Account account = new Account(customer);
        Long accountId = new Random().nextLong();
        account.setId(accountId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        accountService.createAccount(createAccountDTO);

        verify(accountRepository).save(any(Account.class));
        verify(notificationProducer).sendNotification(notificationContentDTOArgumentCaptor.capture());

        NotificationContentDTO content = notificationContentDTOArgumentCaptor.getValue();
        assertEquals(content.getAccountId(), accountId);
        assertEquals(content.getType(), NotificationContentType.CREATE_TRANSACTION);
        assertEquals(content.getAmount(), 10L);


    }

    @Test(expectedExceptions = CustomerNotFoundException.class)
    public void testCreateAccount_CustomerNotFound() {
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        accountService.createAccount(createAccountDTO);

        verifyNoInteractions(accountRepository);
    }

    @Test
    public void testGetAccountsByCustomerId() {

        Account account = new Account(customer);
        List<Account> accountList = List.of(account);
        AccountDTO accountDTO = new AccountDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerId);
        accountDTO.setCustomer(customerDTO);
        List<AccountDTO> accountDTOList = List.of(accountDTO);

        when(accountRepository.findAllByCustomerId(customerId)).thenReturn(accountList);
        when(accountMapper.toDto(accountList)).thenReturn(accountDTOList);

        List<AccountDTO> accounts = accountService.getAccountsByCustomerId(customerId);

        assertEquals(accounts.size(), 1);
        assertEquals(accounts.getFirst(), accountDTO);
        verify(accountRepository).findAllByCustomerId(customerId);
        verify(accountMapper).toDto(accountList);
    }
}
