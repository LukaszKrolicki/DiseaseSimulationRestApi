package com.rest.diseasesimulapi.repositories;

import com.rest.diseasesimulapi.domain.Entities.SimulationResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimulationResultRepository extends JpaRepository<SimulationResultEntity, UUID> {
}
