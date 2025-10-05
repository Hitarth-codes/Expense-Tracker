package com.hitarth.my_expense_tracker.handler;

public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(String message) {
        super(message);
    }

}
