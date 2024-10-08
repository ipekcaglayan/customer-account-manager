package com.assignment.accountmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

}
