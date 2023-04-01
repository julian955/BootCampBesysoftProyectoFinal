package com.besysoft.integrador.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apellido;
    @Column(length = 15)
    private String celular;
    private String calle;
    @Column(name = "codigo_postal")
    private String codigoPostal;
    private String departamento;
    private String localidad;
    private String numero;
    private String piso;
    @Column(name = "nombres")
    private String nombre;
    @Column(name = "tipo_empleado")
    private String tipoEmpleado;
}
