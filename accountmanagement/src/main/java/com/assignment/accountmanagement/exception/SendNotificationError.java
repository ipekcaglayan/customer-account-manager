package com.assignment.accountmanagement.exception;

public class SendNotificationError extends RuntimeException {
    private String message;

    public SendNotificationError(String message){
        super(message);
        this.message = message;
    }
}
