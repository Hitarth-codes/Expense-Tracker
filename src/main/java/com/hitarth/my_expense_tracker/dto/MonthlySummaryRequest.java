package com.hitarth.my_expense_tracker.dto;

public class MonthlySummaryRequest {
    private int month;
    private int year;

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
