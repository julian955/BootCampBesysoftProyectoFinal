package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.Empleado;
import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.re.EmpleadoRE;

import java.util.List;


public interface IEmpleadoMapper {

    Empleado mapToEntity(EmpleadoRE empleadoRE);

    EmpleadoDTO mapToDTO(Empleado empleado);

    EmpleadoDTO mapToBasicDTO(Empleado empleado);

    List<EmpleadoDTO> listToDTO(List<Empleado> list);
}
