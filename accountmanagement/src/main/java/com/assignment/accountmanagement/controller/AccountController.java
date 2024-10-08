package com.assignment.accountmanagement.controller;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CreateAccountDTO;
import com.assignment.accountmanagement.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;


    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountDTO createAccountDTO) {
        log.info("REST request to create an account: {}", createAccountDTO.toString());
        AccountDTO result = accountService.createAccount(createAccountDTO);
        return ResponseEntity.ok().body(result);
    }
}
