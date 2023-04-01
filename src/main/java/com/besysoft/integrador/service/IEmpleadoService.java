package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.re.EmpleadoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;

public interface IEmpleadoService {

    EmpleadoDTO createEmpleado(EmpleadoRE empleadoRE);

    EmpleadoDTO updateEmpleado(Long id, EmpleadoRE empleadoRE) throws EntityNotFoundException;

    EmpleadoDTO getEmpleadoById(Long id) throws EntityNotFoundException;

    Boolean deleteEmpleado(Long id) throws EntityNotFoundException;

    List<EmpleadoDTO> getAllEmpleado();
}
