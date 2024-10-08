package com.assignment.accountmanagement.service;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CreateAccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(CreateAccountDTO createAccountDTO);
    List<AccountDTO> getAccountsByCustomerId(Long customerId);
}
