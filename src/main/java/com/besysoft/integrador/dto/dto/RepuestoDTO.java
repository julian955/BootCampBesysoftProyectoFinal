package com.besysoft.integrador.dto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepuestoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String repuesto;
    private Double valor;
}
