package com.besysoft.integrador.dto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {

    private Long id;
    private String apellido;
    private String celular;
    private String calle;
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String numero;
    private String piso;
    private String email;
    private String nombre;
    private String telefono;
    private List<VehiculoDTO> vehiculosDTO;
}
