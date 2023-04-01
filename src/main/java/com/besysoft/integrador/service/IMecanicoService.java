package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.MecanicoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;

public interface IMecanicoService {

    MecanicoDTO createMecanico(MecanicoRE mecanicoRE);

    MecanicoDTO updateMecanico(Long id, MecanicoRE mecanicoRE) throws EntityNotFoundException;

    MecanicoDTO getMecanicoById(Long id) throws EntityNotFoundException;

    Boolean deleteMecanico(Long id) throws EntityNotFoundException;

    List<MecanicoDTO> getAllMecanico();
}
