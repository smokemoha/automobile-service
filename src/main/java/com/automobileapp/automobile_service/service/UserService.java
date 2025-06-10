package com.automobileapp.automobile_service.service;

import com.automobileapp.automobile_service.model.User;
import com.automobileapp.automobile_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        // You might add validation or password hashing here before saving
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // You can add more specific business logic methods here, e.g.:
    // public Optional<User> findByUsername(String username) {
    //     return userRepository.findByUsername(username);
    // }
}
