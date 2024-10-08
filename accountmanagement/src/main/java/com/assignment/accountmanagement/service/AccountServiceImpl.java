package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CreateAccountDTO;
import com.assignment.accountmanagement.exception.CustomerNotFoundException;
import com.assignment.accountmanagement.mapper.AccountMapper;
import com.assignment.accountmanagement.messaging.NotificationContentDTO;
import com.assignment.accountmanagement.messaging.NotificationContentType;
import com.assignment.accountmanagement.messaging.NotificationProducer;
import com.assignment.accountmanagement.model.Account;
import com.assignment.accountmanagement.model.Customer;
import com.assignment.accountmanagement.repository.AccountRepository;
import com.assignment.accountmanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountMapper accountMapper;
    private final NotificationProducer notificationProducer;

    @Override
    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        Long customerId = createAccountDTO.getCustomerId();
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(String.format("Customer not found with the given id: %s", customerId));
        }
        Account account = accountRepository.save(new Account(customer.get()));
        // #TODO trigger transaction creation if initial credit is not 0

        return accountMapper.toDto(account);
    }

    @Override
    public List<AccountDTO> getAccountsByCustomerId(Long customerId) {
        return accountMapper.toDto(accountRepository.findAllByCustomerId(customerId));
    }
}
