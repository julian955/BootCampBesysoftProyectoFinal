package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRE {


    @NotBlank(message = "El apellido no puede estar vacio.")
    private String apellido;
    private String celular;
    private String calle;
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String numero;
    private String piso;
    @NotBlank(message = "El email no puede estar vacio.")
    @Email
    private String email;
    @NotBlank(message = "El nombre no puede estar vacio.")
    private String nombre;
    private String telefono;
    private List<Long> vehiculosId;
}
