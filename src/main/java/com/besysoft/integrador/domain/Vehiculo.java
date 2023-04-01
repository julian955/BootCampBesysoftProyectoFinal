package com.besysoft.integrador.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer anio;
    private String color;
    private String marca;
    private String modelo;
    @Column(unique = true)
    private String patente;
    @ManyToMany(mappedBy = "vehiculos" , fetch = FetchType.EAGER)
    private List<Cliente> clientes;


}
