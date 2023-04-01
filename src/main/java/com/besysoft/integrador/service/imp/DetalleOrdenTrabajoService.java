package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.DetalleOrdenTrabajo;
import com.besysoft.integrador.dto.dto.DetalleOrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.DetalleOrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IDetalleOrdenTrabajoMapper;
import com.besysoft.integrador.repository.DetalleOrdenTrabajoRepository;
import com.besysoft.integrador.service.IDetalleOrdenTrabajoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DetalleOrdenTrabajoService implements IDetalleOrdenTrabajoService {

    private DetalleOrdenTrabajoRepository repository;
    private IDetalleOrdenTrabajoMapper mapper;

    @Override
    public DetalleOrdenTrabajoDTO createDetalle(DetalleOrdenTrabajoRE detalleOrdenTrabajoRE) throws EntityNotFoundException {

        DetalleOrdenTrabajo entity = mapper.mapToEntity(detalleOrdenTrabajoRE);
        repository.save(entity);
        DetalleOrdenTrabajoDTO response = mapper.mapToDTO(entity);
        return response;
    }

    @Override
    public DetalleOrdenTrabajoDTO updateDetalle(Long id, DetalleOrdenTrabajoRE detalleOrdenTrabajoRE) throws EntityNotFoundException {

        Optional<DetalleOrdenTrabajo> detalleOpt = repository.findById(id);

        if(detalleOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el detalle de la orden de trabajo");
        }

        DetalleOrdenTrabajo detalle = detalleOpt.get();

        if(detalleOrdenTrabajoRE.getCantidad() != null){
            detalle.setCantidad(detalleOrdenTrabajoRE.getCantidad());
        }
        if(detalleOrdenTrabajoRE.getTotal() != null){
            detalle.setTotal(detalleOrdenTrabajoRE.getTotal());
        }
        repository.save(detalle);

        return mapper.mapToDTO(detalle);
    }

    @Override
    public DetalleOrdenTrabajoDTO getDetalleById(Long id) throws EntityNotFoundException {

        Optional<DetalleOrdenTrabajo> detalleOpt = repository.findById(id);

        if(detalleOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el detalle de la orden de trabajo");
        }

        DetalleOrdenTrabajo detalle = detalleOpt.get();

        return mapper.mapToDTO(detalle);
    }

    @Override
    public Boolean deleteDetalle(Long id) throws EntityNotFoundException {

        Optional<DetalleOrdenTrabajo> detalleOpt = repository.findById(id);

        if(detalleOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el detalle de la orden de trabajo");
        }

        repository.deleteById(id);

        return true;
    }

    @Override
    public List<DetalleOrdenTrabajoDTO> getAllDetalle() {

        List<DetalleOrdenTrabajo> list = repository.findAll();

        return mapper.listToDTO(list);
    }
}
