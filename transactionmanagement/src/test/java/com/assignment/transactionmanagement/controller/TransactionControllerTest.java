package com.assignment.transactionmanagement.controller;

import com.assignment.transactionmanagement.dto.CreateTransactionDTO;
import com.assignment.transactionmanagement.dto.TransactionDTO;
import com.assignment.transactionmanagement.service.TransactionService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

@Listeners(MockitoTestNGListener.class)
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Test
    public void testCreateTransaction() {
        CreateTransactionDTO createTransactionDTO = new CreateTransactionDTO(1L, 100L);
        TransactionDTO transactionDTO = new TransactionDTO();

        when(transactionService.createTransaction(createTransactionDTO)).thenReturn(transactionDTO);

        ResponseEntity<TransactionDTO> response = transactionController.createTransaction(createTransactionDTO);

        verify(transactionService, times(1)).createTransaction(createTransactionDTO);
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(response.getBody().getAccountId(), transactionDTO.getAccountId());
        assertEquals(response.getBody().getAmount(), transactionDTO.getAmount());
    }

    @Test
    public void testGetTransactionsByAccountId() {
        Long accountId = 1L;
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        transactionDTOs.add(new TransactionDTO());

        when(transactionService.getTransactionsByAccountId(accountId)).thenReturn(transactionDTOs);

        ResponseEntity<List<TransactionDTO>> response = transactionController.getTransactionsByAccountId(accountId);

        verify(transactionService, times(1)).getTransactionsByAccountId(accountId);
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(response.getBody().size(), transactionDTOs.size());

    }
}
