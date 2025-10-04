package com.hitarth.my_expense_tracker.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hitarth.my_expense_tracker.Repository.ExpenseRepository;
import com.hitarth.my_expense_tracker.Repository.UserRepository;
import com.hitarth.my_expense_tracker.dto.ExpenseRequest;
import com.hitarth.my_expense_tracker.dto.ExpenseResponse;
import com.hitarth.my_expense_tracker.dto.TotalExpense;
import com.hitarth.my_expense_tracker.entity.Expense;
import com.hitarth.my_expense_tracker.entity.User;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ExpenseService {

    private final UserRepository userRepo;
    private final ExpenseRepository expenseRepo;

    public ExpenseService(UserRepository userRepo, ExpenseRepository expenseRepo) {
        this.userRepo = userRepo;
        this.expenseRepo = expenseRepo;
    }

    public ExpenseResponse updateExpense(Long id, String username, ExpenseRequest expenseRequest) {
        Expense expense = expenseRepo.findById(id).orElseThrow(() -> new RuntimeException("Expense Not Found"));
        if (!expense.getUser().getUserName().equals(username)) throw new AccessDeniedException("Unauthorized");

        if (expenseRequest.amount() != null) expense.setAmount(expenseRequest.amount());
        if (expenseRequest.category() != null) expense.setCategory(expenseRequest.category());
        if (expenseRequest.note() != null) expense.setNote(expenseRequest.note());
        if (expenseRequest.expenseDate() != null) expense.setExpenseDate(expenseRequest.expenseDate());
        if (expenseRequest.item() != null) expense.setItem(expenseRequest.item());

        expenseRepo.save(expense);
        return toResponse(expense);
    }

    public void deleteExpense(Long id, String username) {
        Expense expense = expenseRepo.findById(id).orElseThrow(() -> new RuntimeException("Expense Not Found"));
        if (!expense.getUser().getUserName().equals(username)) throw new AccessDeniedException("Unauthorized");
        expenseRepo.delete(expense);
    }

    public ExpenseResponse addExpense(ExpenseRequest expenseRequest, String username) {
        log.info("Inside addExpense");
        User user = userRepo.findByUserName(username).orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        Expense expense = new Expense();
        expense.setAmount(expenseRequest.amount());
        expense.setCategory(expenseRequest.category());
        expense.setNote(expenseRequest.note());
        expense.setExpenseDate(expenseRequest.expenseDate() != null ? expenseRequest.expenseDate() : LocalDateTime.now());
        expense.setUser(user);
        expense.setItem(expenseRequest.item());
        expenseRepo.save(expense);
        return toResponse(expense);
    }

    private ExpenseResponse toResponse(Expense e) {
        return new ExpenseResponse(e.getId(), e.getCategory(), e.getNote(), e.getAmount(), e.getCreatedAt(), e.getItem());
    }

    public List<TotalExpense> getExpenses(User user, LocalDateTime startDate, LocalDateTime endDate) {
        log.info("Inside getExpenses");
        List<Expense> expenses = expenseRepo.findByUsernameAndDateRange(user,startDate,endDate);
        return expenses.stream().map(this::toTotalExpense).toList();
    }

    private TotalExpense toTotalExpense(Expense e) {
        return new TotalExpense(e.getId(), e.getAmount(), e.getCategory(), e.getNote(), e.getItem(), e.getExpenseDate(), e.getCreatedAt(), e.getUser().getId());
    }

}
