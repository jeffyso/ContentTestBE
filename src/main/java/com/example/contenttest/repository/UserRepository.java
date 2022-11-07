package com.example.contenttest.repository;

import com.example.contenttest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByUsernameAndPassword(String username, String password);
}
