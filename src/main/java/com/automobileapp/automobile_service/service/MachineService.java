package com.automobileapp.automobile_service.service;

import com.automobileapp.automobile_service.model.Machine;
import com.automobileapp.automobile_service.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    private final MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine saveMachine(Machine machine) {
        // You might add validation or other business logic here before saving
        return machineRepository.save(machine);
    }

    public Optional<Machine> findMachineById(Long id) {
        return machineRepository.findById(id);
    }

    public List<Machine> findAllMachines() {
        return machineRepository.findAll();
    }

    public void deleteMachine(Long id) {
        machineRepository.deleteById(id);
    }

    // You can add more specific business logic methods here, e.g.:
    // public List<Machine> findMachinesByStatus(String status) {
    //     return machineRepository.findByStatus(status);
    // }
}
