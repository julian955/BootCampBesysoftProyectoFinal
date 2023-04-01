package com.besysoft.integrador.dto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManoObraDTO {

    private Long id;
    private String detalle;
    private LocalDateTime duracion;
    private MecanicoDTO mecanicoDTO;
    private OrdenTrabajoDTO ordenTrabajoDTO;
}
