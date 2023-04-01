package com.besysoft.integrador.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ordenes_trabajo")
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cantidad_cuotas")
    private Integer cantidadCuota;
    @Column(name = "detalle_falla")
    private String falla;
    private String estado;
    @Column(name = "fecha_fin_reparacion")
    private LocalDate finReparacion;
    @Column(name = "fecha_ingreso")
    private LocalDate ingreso;
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;
    @Column(name = "forma_pago")
    private String formaPago;
    @Column(name = "importe_total")
    private Double importe;
    private Long kilometraje;
    @Column(name = "nivel_combustible")
    private String nivelCombustible;
    @Column(name = "tipo_tarjeta")
    private String tipoTarjeta;

    @ManyToOne
    @JoinColumn(name = "administrativo_id")
    private Empleado administrativo;
    @ManyToOne
    @JoinColumn(name = "recepcionista_id")
    private Empleado recepcionista;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
}
