package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.RepuestoDTO;
import com.besysoft.integrador.dto.re.RepuestoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;

public interface IRepuestoService {

    RepuestoDTO createRepuesto(RepuestoRE repuestoRE);

    RepuestoDTO updateRepuesto(Long id, RepuestoRE repuestoRE) throws EntityNotFoundException;

    RepuestoDTO getRepuestoById(Long id) throws EntityNotFoundException;

    Boolean deleteRepuesto(Long id) throws EntityNotFoundException;

    List<RepuestoDTO> getAllRepuesto();
}
