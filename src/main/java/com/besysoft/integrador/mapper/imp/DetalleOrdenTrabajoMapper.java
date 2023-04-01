package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.DetalleOrdenTrabajo;
import com.besysoft.integrador.domain.OrdenTrabajo;
import com.besysoft.integrador.domain.Repuesto;
import com.besysoft.integrador.dto.dto.DetalleOrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.DetalleOrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IDetalleOrdenTrabajoMapper;
import com.besysoft.integrador.mapper.IOrdenTrabajoMapper;
import com.besysoft.integrador.mapper.IRepuestoMapper;
import com.besysoft.integrador.repository.OrdenTrabajoRepository;
import com.besysoft.integrador.repository.RepuestoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DetalleOrdenTrabajoMapper implements IDetalleOrdenTrabajoMapper {


    private OrdenTrabajoRepository ordenTrabajoRepository;
    private RepuestoRepository repuestoRepository;
    private IRepuestoMapper repuestoMapper;
    private IOrdenTrabajoMapper ordenTrabajoMapper;

    @Override
    public DetalleOrdenTrabajo mapToEntity(DetalleOrdenTrabajoRE detalleOrdenTrabajoRE) throws EntityNotFoundException {
        return DetalleOrdenTrabajo.builder()
                .cantidad(detalleOrdenTrabajoRE.getCantidad())
                .total(detalleOrdenTrabajoRE.getTotal())
                .ordenTrabajo(this.obtenerOrdenTrabajo(detalleOrdenTrabajoRE.getOrdenTrabajoId()))
                .repuesto(this.obtenerRepuesto(detalleOrdenTrabajoRE.getRepuestoId()))
                .build();
    }

    @Override
    public DetalleOrdenTrabajoDTO mapToDTO(DetalleOrdenTrabajo entity) {
        return DetalleOrdenTrabajoDTO.builder()
                .id(entity.getId())
                .cantidad(entity.getCantidad())
                .total(entity.getTotal())
                .ordenTrabajoDTO(ordenTrabajoMapper.mapToBasicDTO(entity.getOrdenTrabajo()))
                .repuestoDTO(repuestoMapper.mapToDTO(entity.getRepuesto()))
                .build();
    }


    @Override
    public List<DetalleOrdenTrabajoDTO> listToDTO(List<DetalleOrdenTrabajo> entityList) {
        return entityList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private OrdenTrabajo obtenerOrdenTrabajo(Long id) throws EntityNotFoundException {
        Optional<OrdenTrabajo> ordenTrabajo = ordenTrabajoRepository.findById(id);

        if(ordenTrabajo.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo ingresada");
        }

        return ordenTrabajo.get();
    }

    private Repuesto obtenerRepuesto(Long id) throws EntityNotFoundException {

        Optional<Repuesto> repuesto = repuestoRepository.findById(id);
        if (repuesto.isEmpty()){
            throw new EntityNotFoundException("No se encontro el repuesto ingresado");
        }
        return repuesto.get();
    }
}
