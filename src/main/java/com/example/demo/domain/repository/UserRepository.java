package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findById(int id);
}
