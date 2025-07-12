package com.hitarth.my_expense_tracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.*;

@Data
@Entity 
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String userName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @CreatedBy
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime modifiedAt;
  
}

