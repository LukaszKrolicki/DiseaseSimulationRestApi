package com.rest.diseasesimulapi.controllers;

import com.rest.diseasesimulapi.domain.Dtos.SimulationDTO;
import com.rest.diseasesimulapi.services.SimulationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/simulation/")
public class SimulationController {

    private final SimulationService simulationService;
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }
    @PostMapping("/createSimulation")
    private ResponseEntity<?> createSimulation(@RequestBody SimulationDTO simulationDTO) {
        return new ResponseEntity<>(simulationService.createSimulation(simulationDTO),org.springframework.http.HttpStatus.CREATED);
    }

    @GetMapping("/getSimulationResults/{id}")
    private ResponseEntity<?> SimulationResults(@PathVariable("id") UUID simulationId) {
        return new ResponseEntity<>(simulationService.getSimulationResults(simulationId), HttpStatus.FOUND);
    }

    @PutMapping("/updateSimulation/{id}")
    private ResponseEntity<?> updateSimulation(@PathVariable("id") UUID simulationId, @RequestBody SimulationDTO simulationDTO) {
        return new ResponseEntity<>(simulationService.updateSimulation(simulationId, simulationDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deleteSimulation/{id}")
    private ResponseEntity<?> deleteSimulation(@PathVariable("id") UUID simulationId) {
        return new ResponseEntity<>(simulationService.deleteSimulation(simulationId), HttpStatus.OK);
    }
}
