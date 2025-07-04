package com.example.test2.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test2.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
