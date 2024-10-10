package com.assignment.transactionmanagement.service;

import com.assignment.transactionmanagement.dto.CreateTransactionDTO;
import com.assignment.transactionmanagement.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO);
    List<TransactionDTO> getTransactionsByAccountId(Long accountId);
}
