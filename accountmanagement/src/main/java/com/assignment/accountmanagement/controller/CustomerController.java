package com.assignment.accountmanagement.controller;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.dto.CustomerDTO;
import com.assignment.accountmanagement.service.AccountService;
import com.assignment.accountmanagement.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        log.info("REST request to get all customers");
        List<CustomerDTO> result = customerService.getAllCustomers();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}/accounts")
    public ResponseEntity<List<AccountDTO>> getAllCustomerAccounts(@PathVariable("id") final Long id) {
        log.info("REST request to get all accounts of the customer with id: {}", id);
        List<AccountDTO> result = accountService.getAccountsByCustomerId(id);
        return ResponseEntity.ok().body(result);
    }
}
