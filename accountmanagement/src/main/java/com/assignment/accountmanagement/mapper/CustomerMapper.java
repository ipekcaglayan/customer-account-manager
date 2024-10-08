package com.assignment.accountmanagement.mapper;

import com.assignment.accountmanagement.dto.CustomerDTO;
import com.assignment.accountmanagement.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
}
