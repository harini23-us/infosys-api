package com.springboot.demo.service;


import com.springboot.demo.model.Users;
import com.springboot.demo.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {


    @Autowired
    private UsersRepo userRepository;

    public Users signup(Users user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public Optional<Users> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public void resetPassword(String email, String newPassword) {
        Optional<Users> user = findByEmail(email);
        user.ifPresent(u -> {
            u.setPassword(newPassword);
            userRepository.save(u);
        });
    }
}
