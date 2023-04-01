package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.Mecanico;
import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.MecanicoRE;
import com.besysoft.integrador.mapper.IMecanicoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MecanicoMapper implements IMecanicoMapper {

    @Override
    public Mecanico mapToEntity(MecanicoRE mecanicoRE) {
        return Mecanico.builder()
                .activo(mecanicoRE.getActivo())
                .apellido(mecanicoRE.getApellido())
                .celular(mecanicoRE.getCelular())
                .calle(mecanicoRE.getCalle())
                .codigoPostal(mecanicoRE.getCodigoPostal())
                .departamento(mecanicoRE.getDepartamento())
                .localidad(mecanicoRE.getLocalidad())
                .numero(mecanicoRE.getNumero())
                .piso(mecanicoRE.getPiso())
                .especialidad(mecanicoRE.getEspecialidad())
                .nombre(mecanicoRE.getNombre())
                .build();
    }

    @Override
    public MecanicoDTO mapToDTO(Mecanico mecanico) {
        return MecanicoDTO.builder()
                .id(mecanico.getId())
                .activo(mecanico.getActivo())
                .apellido(mecanico.getApellido())
                .celular(mecanico.getCelular())
                .calle(mecanico.getCalle())
                .codigoPostal(mecanico.getCodigoPostal())
                .departamento(mecanico.getDepartamento())
                .localidad(mecanico.getLocalidad())
                .numero(mecanico.getNumero())
                .piso(mecanico.getPiso())
                .especialidad(mecanico.getEspecialidad())
                .nombre(mecanico.getNombre())
                .build();
    }

    @Override
    public MecanicoDTO mapToBasicDTO(Mecanico mecanico) {
        return MecanicoDTO.builder()
                .id(mecanico.getId())
                .nombre(mecanico.getNombre())
                .apellido(mecanico.getApellido())
                .especialidad(mecanico.getEspecialidad())
                .build();
    }

    @Override
    public List<MecanicoDTO> listToDTO(List<Mecanico> list) {
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
