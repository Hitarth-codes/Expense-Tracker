package com.hitarth.my_expense_tracker.dto;

import java.time.LocalDateTime;

public record ExpenseRequest(
    String category,
    String note,
    Double amount,
    LocalDateTime expenseDate,
    String item
) {}
