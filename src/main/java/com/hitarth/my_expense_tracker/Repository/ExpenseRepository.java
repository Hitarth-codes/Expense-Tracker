package com.hitarth.my_expense_tracker.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hitarth.my_expense_tracker.entity.Expense;
import com.hitarth.my_expense_tracker.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    Optional<Expense> findById(Long id);
    
    @Query("SELECT e FROM Expense e WHERE e.user = :user AND e.expenseDate BETWEEN :startDate AND :endDate")
    List<Expense> findByUsernameAndDateRange(@Param("user") User user, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT e.category, SUM(e.amount) " +
       "FROM Expense e " +
       "WHERE e.user = :user " +
       "AND FUNCTION('strftime', '%m', e.expenseDate) = :month " +
       "AND FUNCTION('strftime', '%Y', e.expenseDate) = :year " +
       "GROUP BY e.category")
    List<Object[]> getCategoryWiseTotals(@Param("user") User user,
                                     @Param("month") String month,
                                     @Param("year") String year);

}
