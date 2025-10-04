package com.hitarth.my_expense_tracker.dto;

public class CategorySummary {
    private String category;
    private Double currentAmount;
    private Double previousAmount;
    private Double percentChange;

    public CategorySummary(String category, Double currentAmount, Double previousAmount, Double percentChange) {
        this.category = category;
        this.currentAmount = currentAmount;
        this.previousAmount = previousAmount;
        this.percentChange = percentChange;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCurrentAmount() {
        return this.currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Double getPreviousAmount() {
        return this.previousAmount;
    }

    public void setPreviousAmount(Double previousAmount) {
        this.previousAmount = previousAmount;
    }

    public Double getPercentChange() {
        return this.percentChange;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

}
