package com.besysoft.integrador.dto.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenTrabajoDTO {

    private Long id;
    private Integer cantidadCuota;
    private String falla;
    private String estado;
    private LocalDate finReparacion;
    private LocalDate ingreso;
    private LocalDate fechaPago;
    private String formaPago;
    private Double importe;
    private Long kilometraje;
    private String nivelCombustible;
    private String tipoTarjeta;
    private EmpleadoDTO administrativoDTO;
    private EmpleadoDTO recepcionistaDTO;
    private VehiculoDTO vehiculoDTO;
}
