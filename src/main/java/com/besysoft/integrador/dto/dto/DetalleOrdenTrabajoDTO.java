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
public class DetalleOrdenTrabajoDTO {

    private Long id;
    private Integer cantidad;
    private Double total;
    private OrdenTrabajoDTO ordenTrabajoDTO;
    private RepuestoDTO repuestoDTO;
}
