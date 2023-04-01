package com.besysoft.integrador.dto.re;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRE {
    @NotBlank(message = "La forma de pago no puede estar vacia.")
    private String formaPago;
    private String tipoTarjeta;
    private Integer cuotas;
}
