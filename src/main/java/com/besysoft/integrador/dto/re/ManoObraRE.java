package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManoObraRE {


    @NotBlank(message = "El detalle no puede estar vacio.")
    private String detalle;
    private LocalDateTime duracion;
    private Long mecanicoId;
    @NotNull(message = "La orden de trabajo no puede estar vacia.")
    private Long ordenTrabajoId;
}
