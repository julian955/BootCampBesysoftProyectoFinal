package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MecanicoRE {


    private Character activo;
    @NotBlank(message = "El apellido no puede estar vacio.")
    private String apellido;
    private String celular;
    private String calle;
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String numero;
    private String piso;
    @NotBlank(message = "La especialidad no puede estar vacia.")
    private String especialidad;
    @NotBlank(message = "El nombre no puede estar vacio.")
    private String nombre;
}
