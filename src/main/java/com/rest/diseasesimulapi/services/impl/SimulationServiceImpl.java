package com.rest.diseasesimulapi.services.impl;

import com.rest.diseasesimulapi.domain.Dtos.SimulationDTO;
import com.rest.diseasesimulapi.domain.Dtos.SimulationResultDTO;
import com.rest.diseasesimulapi.domain.Entities.SimulationEntity;
import com.rest.diseasesimulapi.domain.Entities.SimulationResultEntity;
import com.rest.diseasesimulapi.mappers.Mapper;
import com.rest.diseasesimulapi.repositories.SimulationRepository;
import com.rest.diseasesimulapi.repositories.SimulationResultRepository;
import com.rest.diseasesimulapi.services.SimulationService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final SimulationRepository simulationRepository;

    private final SimulationResultRepository simulationResultRepository;

    private final Mapper<SimulationResultEntity, SimulationResultDTO> simulationResultMapper;

    private final Mapper<SimulationEntity, SimulationDTO> simulationMapper;

    private static final Logger logger = LoggerFactory.getLogger(SimulationServiceImpl.class);

    public SimulationServiceImpl(SimulationRepository simulationRepository, SimulationResultRepository simulationResultRepository, Mapper<SimulationResultEntity, SimulationResultDTO> simulationResultMapper, Mapper<SimulationEntity, SimulationDTO> simulationMapper) {
        this.simulationRepository = simulationRepository;
        this.simulationResultRepository = simulationResultRepository;
        this.simulationResultMapper = simulationResultMapper;
        this.simulationMapper = simulationMapper;
    }
    @Override
    public List<SimulationResultDTO> getSimulationResults(UUID simulationId) {
        SimulationEntity simulation = simulationRepository.findById(simulationId)
                .orElseThrow(() -> new EntityNotFoundException("Simulation with id " + simulationId + " not found"));

        List<SimulationResultEntity> results = simulation.getResults();
        return results.stream()
                .map(simulationResultMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimulationResultDTO> createSimulation(SimulationDTO simulationDTO) {
        SimulationEntity simulation = simulationMapper.mapToEntity(simulationDTO);
        simulationRepository.save(simulation);

        List<SimulationResultEntity> results = runSimulation(simulation);

        simulationResultRepository.saveAll(results);

        List<SimulationResultDTO> resultDTOs = results.stream()
                .map(simulationResultMapper::mapToDto)
                .collect(Collectors.toList());

        return resultDTOs;
    }

    @Override
    public List<SimulationResultDTO> updateSimulation(UUID simulationId, SimulationDTO simulationDTO) {
        SimulationEntity simulation = simulationRepository.findById(simulationId)
                .orElseThrow(() -> new EntityNotFoundException("Simulation with id " + simulationId + " not found"));

        List<SimulationResultEntity> oldresults = new ArrayList<>(simulation.getResults());

        simulationResultRepository.deleteAllInBatch(oldresults);
        oldresults.clear();

        simulation.setN(simulationDTO.getN());
        simulation.setP(simulationDTO.getP());
        simulation.setI(simulationDTO.getI());
        simulation.setR(simulationDTO.getR());
        simulation.setM(simulationDTO.getM());
        simulation.setTi(simulationDTO.getTi());
        simulation.setTm(simulationDTO.getTm());
        simulation.setTs(simulationDTO.getTs());

        simulation = simulationRepository.saveAndFlush(simulation);

        List<SimulationResultEntity> results = runSimulation(simulation);

        simulationResultRepository.saveAll(results);

        return results.stream()
                .map(simulationResultMapper::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public String deleteSimulation(UUID simulationId) {
        simulationRepository.deleteById(simulationId);
        return "Simulation with id " + simulationId + " has been deleted.";
    }
    private List<SimulationResultEntity> runSimulation(SimulationEntity simulation) {
        Long P = simulation.getP();
        Long I = simulation.getI();
        Double R = simulation.getR();
        Double M = simulation.getM();
        Integer Ti = simulation.getTi();
        Integer Tm = simulation.getTm();
        Integer Ts = simulation.getTs();

        Long Pi = I;
        Long Pv = P - I;
        Long Pm = 0L;
        Long Pr = 0L;

        List<SimulationResultEntity> results = new ArrayList<>();

        Long[] newInfections = new Long[Ts];
        Long[] recoveries = new Long[Ts];
        Long[] deaths = new Long[Ts];

        for (int day = 0; day < Ts; day++) {
            newInfections[day] = Math.max(0, Math.min(Math.round(Pi * R), Pv));

            if (day >= Ti) {
                recoveries[day] = Math.min(newInfections[day - Ti], Pi);
            } else {
                recoveries[day] = 0L;
            }

            if (day >= Tm) {
                deaths[day] = Math.min(Math.round(newInfections[day - Tm] * M), Pi);
            } else {
                deaths[day] = 0L;
            }

            Pi = Math.max(0, Pi + newInfections[day] - recoveries[day] - deaths[day]);
            Pv = Math.max(0, Pv - newInfections[day]);
            Pm = Pm + deaths[day];
            Pr = Pr + recoveries[day];

            SimulationResultEntity result = new SimulationResultEntity();
            result.setDay(day + 1L);
            result.setPi(Pi);
            result.setPv(Pv);
            result.setPm(Pm);
            result.setPr(Pr);
            result.setSimulation(simulation);
            results.add(result);
        }

        return results;
    }
}
