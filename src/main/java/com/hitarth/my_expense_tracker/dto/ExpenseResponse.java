package com.hitarth.my_expense_tracker.dto;
import java.time.LocalDateTime;

public record ExpenseResponse(
    Long id,
    String category,
    String note,
    Double amount,
    LocalDateTime date,
    String item
) {}

