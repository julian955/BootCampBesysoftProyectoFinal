package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.Empleado;
import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.re.EmpleadoRE;
import com.besysoft.integrador.mapper.IEmpleadoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpleadoMapper implements IEmpleadoMapper {
    @Override
    public Empleado mapToEntity(EmpleadoRE empleadoRE) {
        return Empleado.builder()
                .apellido(empleadoRE.getApellido())
                .celular(empleadoRE.getCelular())
                .calle(empleadoRE.getCalle())
                .codigoPostal(empleadoRE.getCodigoPostal())
                .departamento(empleadoRE.getDepartamento())
                .localidad(empleadoRE.getLocalidad())
                .numero(empleadoRE.getNumero())
                .piso(empleadoRE.getPiso())
                .nombre(empleadoRE.getNombre())
                .tipoEmpleado(empleadoRE.getTipoEmpleado())
                .build();
    }

    @Override
    public EmpleadoDTO mapToDTO(Empleado empleado) {
        return EmpleadoDTO.builder()
                .id(empleado.getId())
                .apellido(empleado.getApellido())
                .celular(empleado.getCelular())
                .calle(empleado.getCalle())
                .codigoPostal(empleado.getCodigoPostal())
                .departamento(empleado.getDepartamento())
                .localidad(empleado.getLocalidad())
                .numero(empleado.getNumero())
                .piso(empleado.getPiso())
                .nombre(empleado.getNombre())
                .tipoEmpleado(empleado.getTipoEmpleado())
                .build();
    }

    @Override
    public EmpleadoDTO mapToBasicDTO(Empleado empleado) {
        return EmpleadoDTO.builder()
                .id(empleado.getId())
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .tipoEmpleado(empleado.getTipoEmpleado())
                .build();
    }

    @Override
    public List<EmpleadoDTO> listToDTO(List<Empleado> list) {
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
