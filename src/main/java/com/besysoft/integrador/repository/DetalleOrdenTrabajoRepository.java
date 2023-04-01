package com.besysoft.integrador.repository;


import com.besysoft.integrador.domain.DetalleOrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalleOrdenTrabajoRepository extends JpaRepository<DetalleOrdenTrabajo,Long>  {

}
