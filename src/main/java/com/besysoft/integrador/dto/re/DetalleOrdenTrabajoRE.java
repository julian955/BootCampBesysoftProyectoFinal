package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleOrdenTrabajoRE {


    private Integer cantidad;
    @NotNull(message = "El total no puede estar vacio.")
    private Double total;
    @NotNull(message = "La orden de trabajo no puede estar vacia.")
    private Long ordenTrabajoId;
    private Long repuestoId;
}
