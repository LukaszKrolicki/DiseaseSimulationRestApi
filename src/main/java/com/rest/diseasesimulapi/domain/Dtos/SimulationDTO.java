package com.rest.diseasesimulapi.domain.Dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimulationDTO {
    private String N;
    private Long P;
    private Long I;
    private Double R;
    private Double M;
    private Integer Ti;
    private Integer Tm;
    private Integer Ts;
}
