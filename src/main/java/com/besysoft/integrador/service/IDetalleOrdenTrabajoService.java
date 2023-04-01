package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.DetalleOrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.DetalleOrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;

public interface IDetalleOrdenTrabajoService {

    DetalleOrdenTrabajoDTO createDetalle(DetalleOrdenTrabajoRE detalleOrdenTrabajoRE) throws EntityNotFoundException;

    DetalleOrdenTrabajoDTO updateDetalle(Long id, DetalleOrdenTrabajoRE detalleOrdenTrabajoRE) throws EntityNotFoundException;

    DetalleOrdenTrabajoDTO getDetalleById(Long id) throws EntityNotFoundException;

    Boolean deleteDetalle(Long id) throws EntityNotFoundException;

    List<DetalleOrdenTrabajoDTO> getAllDetalle();


}
