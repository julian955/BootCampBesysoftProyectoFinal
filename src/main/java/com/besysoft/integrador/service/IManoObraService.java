package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.ManoObraDTO;
import com.besysoft.integrador.dto.re.ManoObraRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;

public interface IManoObraService {

    ManoObraDTO createManoObra(ManoObraRE manoObraRE) throws EntityNotFoundException;

    ManoObraDTO updateManoObra(Long id, ManoObraRE manoObraRE) throws EntityNotFoundException;

    ManoObraDTO getManoObraById(Long id) throws EntityNotFoundException;

    Boolean deleteManoObra(Long id) throws EntityNotFoundException;

    List<ManoObraDTO> getAllManoObra();

    ManoObraDTO asignarMecanico(Long manoObraId, Long mecanicoId) throws EntityNotFoundException;
}
