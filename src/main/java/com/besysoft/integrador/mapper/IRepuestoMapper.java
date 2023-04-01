package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.Repuesto;
import com.besysoft.integrador.dto.dto.RepuestoDTO;
import com.besysoft.integrador.dto.re.RepuestoRE;


import java.util.List;


public interface IRepuestoMapper {

    Repuesto mapToEntity(RepuestoRE repuestoRE);

    RepuestoDTO mapToDTO(Repuesto repuesto);

    List<RepuestoDTO> listToDTO(List<Repuesto> list);
}
