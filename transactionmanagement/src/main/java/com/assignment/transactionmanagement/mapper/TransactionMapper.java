package com.assignment.transactionmanagement.mapper;

import com.assignment.transactionmanagement.dto.TransactionDTO;
import com.assignment.transactionmanagement.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
}
