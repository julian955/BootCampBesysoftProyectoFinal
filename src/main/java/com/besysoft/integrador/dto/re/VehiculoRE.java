package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoRE {


    private Integer anio;
    private String color;
    @NotBlank(message = "La marca no puede estar vacia.")
    private String marca;
    @NotBlank(message = "El modelo no puede estar vacio.")
    private String modelo;
    @NotBlank(message = "La patente no puede estar vacia.")
    private String patente;
    private List<Long> clientesId;
}
