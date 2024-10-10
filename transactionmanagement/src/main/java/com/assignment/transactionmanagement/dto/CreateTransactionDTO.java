package com.assignment.transactionmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTransactionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NonNull
    private Long accountId;
    @NonNull
    private Long amount;
}

