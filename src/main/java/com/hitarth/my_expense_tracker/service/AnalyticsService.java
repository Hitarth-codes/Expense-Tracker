package com.hitarth.my_expense_tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hitarth.my_expense_tracker.Repository.ExpenseRepository;
import com.hitarth.my_expense_tracker.Repository.UserRepository;
import com.hitarth.my_expense_tracker.dto.CategorySummary;
import com.hitarth.my_expense_tracker.dto.CategoryTotalResponse;
import com.hitarth.my_expense_tracker.dto.MonthlySummaryResponse;
import com.hitarth.my_expense_tracker.dto.TotalExpense;
import com.hitarth.my_expense_tracker.entity.User;
import com.hitarth.my_expense_tracker.handler.InvalidDateRangeException;
import com.hitarth.my_expense_tracker.handler.InvalidMonthOrYearException;
import com.hitarth.my_expense_tracker.handler.NoDataFoundException;
import com.hitarth.my_expense_tracker.handler.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnalyticsService {
    private final UserRepository userRepo;
    private final ExpenseRepository expenseRepo;
    private final ExpenseService expenseService;

    public AnalyticsService(UserRepository userRepo, ExpenseRepository expenseRepo, ExpenseService expenseService) {
        this.userRepo = userRepo;
        this.expenseRepo = expenseRepo;
        this.expenseService = expenseService;
    }
    
    public List<TotalExpense> getExpensesByDate(String username, LocalDateTime startDate, LocalDateTime endDate){
        User user = userRepo.findByUserName(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        if(startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("Start date cannot be after end date");
        }
        List<TotalExpense> expenses = expenseService.getExpenses(user, startDate, endDate);
        if (expenses.isEmpty()) {
            throw new NoDataFoundException("No expenses found for the given date range");
        }
        return expenses;
    }

    public List<CategoryTotalResponse> getCategoryWiseTotals(String username, int month, int year) {
        if (month < 1 || month > 12) {
            throw new InvalidMonthOrYearException("Month must be between 1 and 12");
        }
        if (year < 1900 || year > LocalDateTime.now().getYear()) {
            throw new InvalidMonthOrYearException("Year must be valid and not in the future");
        }
        User user = userRepo.findByUserName(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        
        String monthStr = String.format("%02d", month); // "09"
        String yearStr = String.valueOf(year);
        List<Object[]> results = expenseRepo.getCategoryWiseTotals(user, monthStr, yearStr);
        return results.stream()
                .map(obj -> new CategoryTotalResponse(
                        obj[0].toString(),
                        ((Double) obj[1]).doubleValue()
                )).toList();
    }

    public MonthlySummaryResponse getMonthlySummary(String username, int month, int year){
        if (month < 1 || month > 12) {
            throw new InvalidMonthOrYearException("Month must be between 1 and 12");
        }
        if (year < 1900 || year > LocalDateTime.now().getYear()) {
            throw new InvalidMonthOrYearException("Year must be valid and not in the future");
        }
        User user = userRepo.findByUserName(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        String monthStr = String.format("%02d", month);
        String yearStr = String.valueOf(year);
        List<Object[]> results = expenseRepo.getCategoryWiseTotals(user, monthStr, yearStr);
        Double totalMonthlyExpense = 0.0;
        Map<String, Double> currentMonthMap = new HashMap<>();

        for(Object[] result : results){
            totalMonthlyExpense += (Double) result[1];
            currentMonthMap.put(result[0].toString(),(Double) result[1]);
        }

        int prevMonth = month == 1 ? 12 : month - 1;
        int prevYear = month == 1 ? year - 1 : year;
        String prevMonthStr = String.format("%02d", prevMonth);
        String prevYearStr = String.valueOf(prevYear);

        List<Object[]> prevResults = expenseRepo.getCategoryWiseTotals(user, prevMonthStr, prevYearStr);
        Double totalPrevExpense = 0.0;
        Map<String, Double> prevMonthMap = new HashMap<>();

        for(Object[] result : prevResults){
            totalPrevExpense += (Double) result[1];
            prevMonthMap.put(result[0].toString(),(Double) result[1]);
        }

        List<CategorySummary> categorySummaries = new ArrayList<>();
        Set<String> allCategories = new HashSet<>();
        allCategories.addAll(currentMonthMap.keySet());

        for(String category : allCategories){
            Double currAmount = currentMonthMap.getOrDefault(category,0.0);
            Double prevAmount = prevMonthMap.getOrDefault(category,0.0);
            if (prevAmount == 0.0) {
                categorySummaries.add(new CategorySummary(category, currAmount, prevAmount, null));
                continue;
            }
            Double percentChange = ((currAmount - prevAmount) / prevAmount) * 100.0;
            categorySummaries.add(new CategorySummary(category, currAmount, prevAmount, percentChange));
        }

        return new MonthlySummaryResponse(totalMonthlyExpense, categorySummaries, ((totalMonthlyExpense-totalPrevExpense)/totalMonthlyExpense)*100.0);
    }

}
