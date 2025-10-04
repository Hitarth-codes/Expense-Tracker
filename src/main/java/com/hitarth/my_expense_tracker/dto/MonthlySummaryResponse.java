package com.hitarth.my_expense_tracker.dto;

import java.util.List;

public class MonthlySummaryResponse {
    private Double totalExpenditure;
    private Double percentChange;
    private List<CategorySummary> categorySummaries;

    public MonthlySummaryResponse(Double totalExpenditure, List<CategorySummary> categorySummaries, Double percentChange) {
        this.totalExpenditure = totalExpenditure;
        this.percentChange = percentChange;
        this.categorySummaries = categorySummaries;
    }

    public Double getPercentChange() {
        return this.percentChange;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    public Double getTotalExpenditure() {
        return this.totalExpenditure;
    }

    public void setTotalExpenditure(Double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public List<CategorySummary> getCategorySummaries() {
        return this.categorySummaries;
    }

    public void setCategorySummaries(List<CategorySummary> categorySummaries) {
        this.categorySummaries = categorySummaries;
    }

}
