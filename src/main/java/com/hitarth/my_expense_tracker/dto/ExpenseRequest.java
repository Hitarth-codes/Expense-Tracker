package com.hitarth.my_expense_tracker.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public record ExpenseRequest(
    @NotBlank(message = "Category is mandatory") String category,
    @NotBlank(message = "Note is mandatory")String note,
    @NotBlank(message = "Amount is mandatory") Double amount,
    LocalDateTime expenseDate,
    @NotBlank(message = "Item is mandatory") String item
) {}
