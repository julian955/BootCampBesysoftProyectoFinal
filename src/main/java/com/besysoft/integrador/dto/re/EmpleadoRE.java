package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoRE {


    @NotBlank(message = "El apellido no puede estar vacio.")
    private String apellido;
    private String celular;
    private String calle;
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String numero;
    private String piso;
    @NotBlank(message = "El nombre no puede estar vacio.")
    private String nombre;
    @NotBlank(message = "El tipo de empleado no puede estar vacio.")
    private String tipoEmpleado;
}
