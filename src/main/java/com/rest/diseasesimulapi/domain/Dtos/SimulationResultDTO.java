package com.rest.diseasesimulapi.domain.Dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationResultDTO {
    private UUID id;
    private Long day;
    private Long Pi;
    private Long Pv;
    private Long Pm;
    private Long Pr;
    private UUID simulationId;
}
