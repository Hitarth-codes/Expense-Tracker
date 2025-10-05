package com.hitarth.my_expense_tracker.handler;

public class InvalidMonthOrYearException extends RuntimeException {
    public InvalidMonthOrYearException(String message) {
        super(message);
    }

}
