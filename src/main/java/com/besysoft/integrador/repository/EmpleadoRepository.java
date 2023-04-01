package com.besysoft.integrador.repository;

import com.besysoft.integrador.domain.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
