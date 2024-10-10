package com.assignment.transactionmanagement.service;

import com.assignment.transactionmanagement.dto.CreateTransactionDTO;
import com.assignment.transactionmanagement.dto.TransactionDTO;
import com.assignment.transactionmanagement.mapper.TransactionMapper;
import com.assignment.transactionmanagement.model.Transaction;
import com.assignment.transactionmanagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    @Override
    public TransactionDTO createTransaction(CreateTransactionDTO createTransactionDTO) {
        Transaction transaction = transactionRepository.save(new Transaction(
                createTransactionDTO.getAccountId(),
                createTransactionDTO.getAmount()
        ));

        return transactionMapper.toDto(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactionsByAccountId(Long accountId) {
        return transactionMapper.toDto(
                transactionRepository.findAllByAccountId(accountId)
        );
    }
}
