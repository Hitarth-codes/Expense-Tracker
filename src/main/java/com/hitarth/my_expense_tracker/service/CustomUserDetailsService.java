package com.hitarth.my_expense_tracker.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hitarth.my_expense_tracker.Repository.UserRepository;
import com.hitarth.my_expense_tracker.entity.User;

@Service
public class CustomUserDetailsService {

    @Autowired
    private UserRepository repo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(), user.getPassword(), Collections.emptyList());
    }
}
