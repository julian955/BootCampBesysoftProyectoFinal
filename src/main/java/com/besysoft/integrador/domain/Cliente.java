package com.besysoft.integrador.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80 , nullable = false)
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

    @Column(name = "correo_electronico",unique = true)
    private String email;
    @Column(name = "nombres",length = 100,nullable = false)
    private String nombre;
    @Column(name = "telefono_linea",length = 15)
    private String telefono;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cliente_vehiculo",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "vehiculo_id")
    )
    private List<Vehiculo> vehiculos;

}
