package com.assignment.transactionmanagement.controller;

import com.assignment.transactionmanagement.dto.CreateTransactionDTO;
import com.assignment.transactionmanagement.dto.TransactionDTO;
import com.assignment.transactionmanagement.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody CreateTransactionDTO createAccountDTO) {
        log.info("REST request to create a transaction: {}", createAccountDTO.toString());
        TransactionDTO result = transactionService.createTransaction(createAccountDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(@PathVariable("id") final Long id) {
        log.info("REST request to get transactions of account with id: {}", id);
        List<TransactionDTO> result = transactionService.getTransactionsByAccountId(id);
        return ResponseEntity.ok().body(result);
    }

}
