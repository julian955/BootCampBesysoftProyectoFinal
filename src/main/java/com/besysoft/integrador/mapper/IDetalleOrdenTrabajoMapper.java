package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.DetalleOrdenTrabajo;
import com.besysoft.integrador.dto.dto.DetalleOrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.DetalleOrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;


public interface IDetalleOrdenTrabajoMapper {

    DetalleOrdenTrabajo mapToEntity(DetalleOrdenTrabajoRE detalleOrdenTrabajoRE) throws EntityNotFoundException;

    DetalleOrdenTrabajoDTO mapToDTO(DetalleOrdenTrabajo entity);

    List<DetalleOrdenTrabajoDTO> listToDTO(List<DetalleOrdenTrabajo> entityList);
}
