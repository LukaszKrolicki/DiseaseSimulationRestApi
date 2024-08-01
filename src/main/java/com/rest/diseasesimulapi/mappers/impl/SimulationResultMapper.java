package com.rest.diseasesimulapi.mappers.impl;

import com.rest.diseasesimulapi.domain.Dtos.SimulationResultDTO;
import com.rest.diseasesimulapi.domain.Entities.SimulationResultEntity;
import com.rest.diseasesimulapi.mappers.Mapper;
import jakarta.persistence.Column;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SimulationResultMapper implements Mapper<SimulationResultEntity, SimulationResultDTO> {

    private final ModelMapper mapper;
    public SimulationResultMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SimulationResultDTO mapToDto(SimulationResultEntity simulationResultEntity) {
        return mapper.map(simulationResultEntity, SimulationResultDTO.class);
    }

    @Override
    public SimulationResultEntity mapToEntity(SimulationResultDTO simulationResultDTO) {
        return mapper.map(simulationResultDTO, SimulationResultEntity.class);
    }
}
