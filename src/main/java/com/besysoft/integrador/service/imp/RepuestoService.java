package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.Repuesto;
import com.besysoft.integrador.dto.dto.RepuestoDTO;
import com.besysoft.integrador.dto.re.RepuestoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IRepuestoMapper;
import com.besysoft.integrador.repository.RepuestoRepository;
import com.besysoft.integrador.service.IRepuestoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepuestoService implements IRepuestoService {

    private RepuestoRepository repository;
    private IRepuestoMapper mapper;

    @Override
    public RepuestoDTO createRepuesto(RepuestoRE repuestoRE) {

        Repuesto repuesto = mapper.mapToEntity(repuestoRE);
        repository.save(repuesto);

        return mapper.mapToDTO(repuesto);
    }

    @Override
    public RepuestoDTO updateRepuesto(Long id, RepuestoRE repuestoRE) throws EntityNotFoundException {
        Optional<Repuesto> repuestoOpt = repository.findById(id);

        if(repuestoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el repuesto.");
        }

        Repuesto repuesto = repuestoOpt.get();

        if(repuestoRE.getMarca() != null){
            repuesto.setMarca(repuestoRE.getMarca());
        }
        if(repuestoRE.getModelo() != null){
            repuesto.setModelo(repuestoRE.getModelo());
        }
        if(repuestoRE.getRepuesto() != null){
            repuesto.setRepuesto(repuestoRE.getRepuesto());
        }
        if(repuestoRE.getValor() != null){
            repuesto.setValor(repuestoRE.getValor());
        }

        repository.save(repuesto);

        return mapper.mapToDTO(repuesto);
    }

    @Override
    public RepuestoDTO getRepuestoById(Long id) throws EntityNotFoundException {

        Optional<Repuesto> repuestoOpt = repository.findById(id);

        if(repuestoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el repuesto.");
        }

        return mapper.mapToDTO(repuestoOpt.get());
    }

    @Override
    public Boolean deleteRepuesto(Long id) throws EntityNotFoundException {

        Optional<Repuesto> repuestoOpt = repository.findById(id);

        if(repuestoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el repuesto.");
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<RepuestoDTO> getAllRepuesto() {
        return mapper.listToDTO(repository.findAll());
    }
}
