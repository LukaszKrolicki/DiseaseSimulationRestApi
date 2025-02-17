package com.rest.diseasesimulapi.domain.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "simulations")
public class SimulationEntity {

    @Id
    @GeneratedValue()
    private UUID simulationId;

    @Column(nullable = false, name = "simulation_name")
    private String N;

    @Column(nullable = false, name = "population_size")
    private Long P;

    @Column(nullable = false, name = "initial_infected")
    private Long I;

    @Column(nullable = false, name = "rFactor") //factor that indicates how many people an infected person infects
    private Double R;

    @Column(nullable = false, name = "mortality_rate")
    private Double M;

    @Column(nullable = false, name = "recovery_time") //how many days it takes for a person to recover
    private Integer Ti;

    @Column(nullable = false, name = "death_time") //how many days it takes for a person to die
    private Integer Tm;

    @Column(nullable = false, name = "simulation_duration")
    private Integer Ts;

    @OneToMany(mappedBy = "simulation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SimulationResultEntity> results;
}
