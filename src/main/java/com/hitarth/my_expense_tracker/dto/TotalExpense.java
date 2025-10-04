package com.hitarth.my_expense_tracker.dto;

import java.time.LocalDateTime;

public class TotalExpense {
    private Long id;
    private Double amount;
    private String category;
    private String note;
    private String item;
    private LocalDateTime expenseDate;
    private LocalDateTime createdAt;
    private Long userId;

    public TotalExpense(Long id, Double amount, String category, String note, String item, LocalDateTime expenseDate, LocalDateTime createdAt, Long userId) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.item = item;
        this.expenseDate = expenseDate;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getItem() {
        return this.item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public LocalDateTime getExpenseDate() {
        return this.expenseDate;
    }

    public void setExpenseDate(LocalDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
