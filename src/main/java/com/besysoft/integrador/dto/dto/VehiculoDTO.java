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
public class VehiculoDTO {

    private Long id;
    private Integer anio;
    private String color;
    private String marca;
    private String modelo;
    private String patente;
    private List<ClienteDTO> clientesDTO;
}
