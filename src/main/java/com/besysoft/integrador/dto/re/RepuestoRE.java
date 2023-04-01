package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepuestoRE {


    private String marca;
    private String modelo;
    @NotBlank(message = "El tipo de repuesto no puede estar vacio.")
    private String repuesto;
    @NotNull(message = "El valor no puede estar vacio.")
    private Double valor;
}
