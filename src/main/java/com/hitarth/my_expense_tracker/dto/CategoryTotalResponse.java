package com.hitarth.my_expense_tracker.dto;

public class CategoryTotalResponse {
    private String category;
    private Double total;

    public CategoryTotalResponse(String category, Double total) {
        this.category = category;
        this.total = total;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
