package com.hitarth.my_expense_tracker.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hitarth.my_expense_tracker.dto.CategoryTotalRequest;
import com.hitarth.my_expense_tracker.dto.CategoryTotalResponse;
import com.hitarth.my_expense_tracker.dto.MonthlySummaryRequest;
import com.hitarth.my_expense_tracker.dto.MonthlySummaryResponse;
import com.hitarth.my_expense_tracker.dto.TotalExpense;
import com.hitarth.my_expense_tracker.entity.Expense;
import com.hitarth.my_expense_tracker.service.AnalyticsService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/analytics")
@RestController
@Slf4j
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) { 
        System.out.println(">>> AnalyticsController initialized");
        this.analyticsService = analyticsService; 
    }

    @GetMapping("/getExpensesByDateRange")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TotalExpense>> getExpensesByDate(@RequestParam("startDate") String start, @RequestParam("endDate") String end) {
        log.info("Inside getExpensesByDate");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        return ResponseEntity.ok(analyticsService.getExpensesByDate(username, startDate, endDate));
    }

    @PostMapping("/pie-chart")
    public ResponseEntity<List<CategoryTotalResponse>> getCategoryWiseTotals(
            @RequestBody CategoryTotalRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CategoryTotalResponse> response =
                analyticsService.getCategoryWiseTotals(username, request.getMonth(), request.getYear());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/summary")
    public ResponseEntity<MonthlySummaryResponse> getMonthlySummary(
            @RequestBody MonthlySummaryRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        MonthlySummaryResponse response =
                analyticsService.getMonthlySummary(username, request.getMonth(), request.getYear());

        return ResponseEntity.ok(response);
    }

}
