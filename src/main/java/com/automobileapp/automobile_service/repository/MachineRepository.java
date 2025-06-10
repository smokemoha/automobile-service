package com.automobileapp.automobile_service.repository;

import com.automobileapp.automobile_service.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    // Spring Data JPA automatically provides common CRUD operations.
    // You can add custom query methods here if needed.
}
