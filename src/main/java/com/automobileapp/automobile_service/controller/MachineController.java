package com.automobileapp.automobile_service.controller;

import com.automobileapp.automobile_service.model.Machine;
import com.automobileapp.automobile_service.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/machines" ) // Base path for all machine-related endpoints
public class MachineController {

    private final MachineService machineService;

    @Autowired
    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @PostMapping
    public ResponseEntity<Machine> createMachine(@RequestBody Machine machine) {
        Machine savedMachine = machineService.saveMachine(machine);
        return new ResponseEntity<>(savedMachine, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Machine>> getAllMachines() {
        List<Machine> machines = machineService.findAllMachines();
        return new ResponseEntity<>(machines, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id) {
        Optional<Machine> machine = machineService.findMachineById(id);
        return machine.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody Machine machine) {
        if (!machineService.findMachineById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        machine.setMachineId(id); // Ensure the ID from the path is used
        Machine updatedMachine = machineService.saveMachine(machine);
        return new ResponseEntity<>(updatedMachine, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMachine(@PathVariable Long id) {
        if (!machineService.findMachineById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        machineService.deleteMachine(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
