package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.Repuesto;
import com.besysoft.integrador.dto.dto.RepuestoDTO;
import com.besysoft.integrador.dto.re.RepuestoRE;
import com.besysoft.integrador.mapper.IRepuestoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepuestoMapper implements IRepuestoMapper {

    @Override
    public Repuesto mapToEntity(RepuestoRE repuestoRE) {
        return Repuesto.builder()
                .marca(repuestoRE.getMarca())
                .modelo(repuestoRE.getModelo())
                .repuesto(repuestoRE.getRepuesto())
                .valor(repuestoRE.getValor())
                .build();
    }

    @Override
    public RepuestoDTO mapToDTO(Repuesto repuesto) {
        return RepuestoDTO.builder()
                .id(repuesto.getId())
                .marca(repuesto.getMarca())
                .modelo(repuesto.getModelo())
                .repuesto(repuesto.getRepuesto())
                .valor(repuesto.getValor())
                .build();
    }

    @Override
    public List<RepuestoDTO> listToDTO(List<Repuesto> list) {
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
