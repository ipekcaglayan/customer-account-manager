package com.assignment.accountmanagement.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NonNull
    private String messageSource;
    @NonNull
    private NotificationContentDTO content;
}
