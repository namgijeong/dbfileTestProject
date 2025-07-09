package com.example.test2.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test2.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdAndPwd(String id, String pwd);
    Page<User> findAllByOrderByRegDateDesc(Pageable pageable);
    long count();
}
