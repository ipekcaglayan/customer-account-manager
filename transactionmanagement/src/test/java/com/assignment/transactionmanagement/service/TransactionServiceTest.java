package com.assignment.transactionmanagement.service;

import com.assignment.transactionmanagement.dto.CreateTransactionDTO;
import com.assignment.transactionmanagement.dto.TransactionDTO;
import com.assignment.transactionmanagement.mapper.TransactionMapper;
import com.assignment.transactionmanagement.model.Transaction;
import com.assignment.transactionmanagement.repository.TransactionRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;


    @Test
    public void testCreateTransaction() {
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(1L, 100L);
        Transaction transaction = new Transaction(1L, 100L);
        TransactionDTO transactionDTO = new TransactionDTO();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(transactionMapper.toDto(transaction)).thenReturn(transactionDTO);

        TransactionDTO result = transactionService.createTransaction(createTransactionDTO);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(transactionMapper, times(1)).toDto(transaction);
        assertEquals(result.getAmount(), transactionDTO.getAmount());
        assertEquals(result.getAccountId(), transactionDTO.getAccountId());
    }

    @Test
    public void testGetTransactionsByAccountId() {
        Long accountId = 1L;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(accountId, 100L));

        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        transactionDTOs.add(new TransactionDTO());

        when(transactionRepository.findAllByAccountId(accountId)).thenReturn(transactions);
        when(transactionMapper.toDto(transactions)).thenReturn(transactionDTOs);

        List<TransactionDTO> result = transactionService.getTransactionsByAccountId(accountId);

        verify(transactionRepository, times(1)).findAllByAccountId(accountId);
        verify(transactionMapper, times(1)).toDto(transactions);
        assertEquals(result.size(), transactionDTOs.size());
        assertEquals(result.get(0).getAmount(), transactionDTOs.get(0).getAmount());
    }
}
