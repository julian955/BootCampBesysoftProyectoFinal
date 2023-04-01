package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.ManoObra;
import com.besysoft.integrador.dto.dto.ManoObraDTO;
import com.besysoft.integrador.dto.re.ManoObraRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;


import java.util.List;


public interface IManoObraMapper {

    ManoObra mapToEntity(ManoObraRE manoObraRE) throws EntityNotFoundException;

    ManoObraDTO mapToDTO(ManoObra manoObra);

    List<ManoObraDTO> listToDTO(List<ManoObra> list);
}
