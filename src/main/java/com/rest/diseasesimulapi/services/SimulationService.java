package com.rest.diseasesimulapi.services;

import com.rest.diseasesimulapi.domain.Dtos.SimulationDTO;
import com.rest.diseasesimulapi.domain.Dtos.SimulationResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SimulationService {
    List<SimulationResultDTO> createSimulation(SimulationDTO simulationDTO);

    List<SimulationResultDTO> getSimulationResults(UUID simulationId);

    List<SimulationResultDTO> updateSimulation(UUID simulationId, SimulationDTO simulationDTO);

    String deleteSimulation(UUID simulationId);
}
