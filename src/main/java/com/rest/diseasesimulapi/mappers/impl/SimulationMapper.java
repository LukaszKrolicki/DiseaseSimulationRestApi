package com.rest.diseasesimulapi.mappers.impl;

import com.rest.diseasesimulapi.domain.Dtos.SimulationDTO;
import com.rest.diseasesimulapi.domain.Entities.SimulationEntity;
import com.rest.diseasesimulapi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SimulationMapper implements Mapper<SimulationEntity, SimulationDTO> {

    private final ModelMapper mapper;

    public SimulationMapper(ModelMapper mapper){
        this.mapper = mapper;
    }
    @Override
    public SimulationDTO mapToDto(SimulationEntity simulationEntity) {
        return mapper.map(simulationEntity, SimulationDTO.class);
    }

    @Override
    public SimulationEntity mapToEntity(SimulationDTO simulationDTO) {
        return mapper.map(simulationDTO, SimulationEntity.class);
    }
}
