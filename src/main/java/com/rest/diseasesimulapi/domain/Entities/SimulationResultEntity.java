package com.rest.diseasesimulapi.domain.Entities;

import com.rest.diseasesimulapi.domain.Entities.SimulationEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "simulation_result")

public class SimulationResultEntity {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(name = "simulation_day", nullable = false)
    private Long day;

    @Column(name = "people_infected", nullable = false)
    private Long Pi;

    @Column(name = "people_vulnerable", nullable = false)
    private Long Pv;

    @Column(name = "people_dead", nullable = false)
    private Long Pm;

    @Column(name = "people_recovered_and_immune", nullable = false)
    private Long Pr;

    @ManyToOne
    @JoinColumn(name = "simulation_id", nullable = false)
    private SimulationEntity simulation;
}
