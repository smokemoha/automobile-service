package com.automobileapp.automobile_service.repository;

import com.automobileapp.automobile_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically provides common CRUD operations.
    // You can add custom query methods here if needed, for example:
    // Optional<User> findByUsername(String username);
    // Optional<User> findByEmail(String email);
}
