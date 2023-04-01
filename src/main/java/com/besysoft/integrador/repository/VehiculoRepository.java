package com.besysoft.integrador.repository;


import com.besysoft.integrador.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo,Long> {

    @Query(value = "SELECT Vehiculo c WHERE c.Cliente.nombre =?",nativeQuery = true)
    List<Vehiculo> findByCliente(String name);

    Optional<Vehiculo> findByPatente(String patente);
}
