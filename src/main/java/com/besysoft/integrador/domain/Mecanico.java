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
@Table(name = "mecanicos")
public class Mecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Character activo;
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
    private String especialidad;
    @Column(name = "nombres")
    private String nombre;
}
