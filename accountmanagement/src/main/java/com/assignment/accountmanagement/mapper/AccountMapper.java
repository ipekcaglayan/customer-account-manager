package com.assignment.accountmanagement.mapper;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {
    @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "offsetDateTimeToString")
    AccountDTO toDto(Account s);

    @Named("offsetDateTimeToString")
    default String mapOffsetDateTimeToString(OffsetDateTime offsetDateTime) {
        return offsetDateTime != null
                ? offsetDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy"))
                : null;
    }
}
