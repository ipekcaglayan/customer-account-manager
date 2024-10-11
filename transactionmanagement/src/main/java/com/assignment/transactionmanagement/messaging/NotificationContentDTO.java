package com.assignment.transactionmanagement.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationContentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NonNull
    private NotificationContentType type;
    @NonNull
    private Long accountId;
    @NonNull
    private Long amount;
}
