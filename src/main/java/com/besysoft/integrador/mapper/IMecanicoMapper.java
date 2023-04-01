package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.Mecanico;
import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.MecanicoRE;


import java.util.List;


public interface IMecanicoMapper {

    Mecanico mapToEntity(MecanicoRE mecanicoRE);

    MecanicoDTO mapToDTO(Mecanico mecanico);

    MecanicoDTO mapToBasicDTO(Mecanico mecanico);

    List<MecanicoDTO> listToDTO(List<Mecanico>list);
}
