package com.example.fantasia_park.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fantasia_park.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    
}
