package com.rest.diseasesimulapi.domain;

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

    @Column(nullable = false, name = "simulation_day")
    private Long day;

    @Column(nullable = false, name = "people_infected")
    private Long Pi;

    @Column(nullable = false, name = "people_vulnerable")
    private Long Pv;

    @Column(nullable = false, name = "people_dead")
    private Long Pm;

    @Column(nullable = false, name = "people_recovered_and_immune")
    private Long Pr;

    @ManyToOne
    @JoinColumn(name = "simulation_id", nullable = false)
    private SimulationEntity simulationParams;
}
