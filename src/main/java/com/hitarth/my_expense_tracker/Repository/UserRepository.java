package com.hitarth.my_expense_tracker.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hitarth.my_expense_tracker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String username);
  boolean existsByUserName(String username);
  boolean existsByEmail(String email);
}
