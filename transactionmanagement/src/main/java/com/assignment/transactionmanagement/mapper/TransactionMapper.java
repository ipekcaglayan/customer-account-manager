package com.assignment.transactionmanagement.mapper;

import com.assignment.transactionmanagement.dto.TransactionDTO;
import com.assignment.transactionmanagement.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "offsetDateTimeToString")
    TransactionDTO toDto(Transaction s);

    @Named("offsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null
                ? offsetDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy | HH:mm"))
                : null;
    }
}
