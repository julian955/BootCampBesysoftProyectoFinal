package com.besysoft.integrador.dto.re;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenTrabajoRE {


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
    private Long administrativoId;
    private Long recepcionistaId;
    @NotNull(message = "El id del vehiculo no puede estar vacio.")
    private Long vehiculoId;
}
