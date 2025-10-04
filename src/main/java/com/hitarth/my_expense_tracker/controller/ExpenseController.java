package com.hitarth.my_expense_tracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.hitarth.my_expense_tracker.dto.ExpenseRequest;
import com.hitarth.my_expense_tracker.dto.ExpenseResponse;
import com.hitarth.my_expense_tracker.service.ExpenseService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/Expenses")
@RestController 
@Slf4j
public class ExpenseController {

  private final ExpenseService expenseService;

  public ExpenseController(ExpenseService svc) { 
    System.out.println(">>> ExpenseController initialized");
    this.expenseService = svc; 
  }
  
  @PostMapping("/addExpense")
  private ResponseEntity<ExpenseResponse> addExpense(@RequestBody ExpenseRequest expenseRequest){
    log.info("Inside addExpense");
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return ResponseEntity.ok(expenseService.addExpense(expenseRequest, username));
  }

  @DeleteMapping("/deleteExpense/{id}")
  private ResponseEntity<Void> deleteExpense(@PathVariable("id") Long id){
    log.info("Inside deleteExpense");
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    expenseService.deleteExpense(id, username);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/updateExpense/{id}")
  private ResponseEntity<ExpenseResponse> updateExpense(@PathVariable("id") Long id, @RequestBody ExpenseRequest expenseRequest){
    log.info("Inside updateExpense");
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    return ResponseEntity.ok(expenseService.updateExpense(id, username, expenseRequest));
  }

}
