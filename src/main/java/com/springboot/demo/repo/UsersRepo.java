package com.springboot.demo.repo;

import com.springboot.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
