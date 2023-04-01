package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.OrdenTrabajo;
import com.besysoft.integrador.dto.dto.OrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.OrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;


import java.util.List;


public interface IOrdenTrabajoMapper {

    OrdenTrabajo mapToEntity(OrdenTrabajoRE ordenTrabajoRE) throws EntityNotFoundException;

    OrdenTrabajoDTO mapToDTO(OrdenTrabajo ordenTrabajo);

    OrdenTrabajoDTO mapToBasicDTO(OrdenTrabajo ordenTrabajo);

    List<OrdenTrabajoDTO> listToDTO(List<OrdenTrabajo> list);
}
