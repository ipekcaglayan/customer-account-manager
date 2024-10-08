package com.assignment.accountmanagement.mapper;

import com.assignment.accountmanagement.dto.AccountDTO;
import com.assignment.accountmanagement.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper extends EntityMapper<AccountDTO, Account> {
}
