package com.hitarth.my_expense_tracker.handler;

public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException(String message) {
        super(message);
    }

}
