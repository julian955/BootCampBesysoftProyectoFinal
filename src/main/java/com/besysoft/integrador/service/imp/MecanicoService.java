package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.Mecanico;
import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.MecanicoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IMecanicoMapper;
import com.besysoft.integrador.repository.MecanicoRepository;
import com.besysoft.integrador.service.IMecanicoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MecanicoService implements IMecanicoService {

    private IMecanicoMapper mapper;
    private MecanicoRepository repository;

    @Override
    public MecanicoDTO createMecanico(MecanicoRE mecanicoRE) {

        Mecanico mecanico = mapper.mapToEntity(mecanicoRE);
        repository.save(mecanico);

        return mapper.mapToDTO(mecanico);
    }

    @Override
    public MecanicoDTO updateMecanico(Long id, MecanicoRE mecanicoRE) throws EntityNotFoundException {

        Optional<Mecanico> mecanicoOpt = repository.findById(id);

        if(mecanicoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el mecanico");
        }

        Mecanico mecanico = mecanicoOpt.get();

        if(mecanicoRE.getActivo() != null){
            mecanico.setActivo(mecanicoRE.getActivo());
        }
        if(mecanicoRE.getApellido() != null){
            mecanico.setApellido(mecanicoRE.getApellido());
        }
        if(mecanicoRE.getCelular() != null){
            mecanico.setCelular(mecanicoRE.getCelular());
        }
        if(mecanicoRE.getCalle() != null){
            mecanico.setCalle(mecanicoRE.getCalle());
        }
        if(mecanicoRE.getCodigoPostal() != null){
            mecanico.setCodigoPostal(mecanicoRE.getCodigoPostal());
        }
        if(mecanicoRE.getDepartamento() != null){
            mecanico.setDepartamento(mecanicoRE.getDepartamento());
        }
        if(mecanicoRE.getLocalidad() != null){
            mecanico.setLocalidad(mecanicoRE.getLocalidad());
        }
        if(mecanicoRE.getNumero() != null){
            mecanico.setNumero(mecanicoRE.getNumero());
        }
        if(mecanicoRE.getPiso() != null){
            mecanico.setPiso(mecanicoRE.getPiso());
        }
        if(mecanicoRE.getEspecialidad() != null){
            mecanico.setEspecialidad(mecanicoRE.getEspecialidad());
        }
        if(mecanicoRE.getNombre() != null){
            mecanico.setNombre(mecanicoRE.getNombre());
        }

        repository.save(mecanico);

        return mapper.mapToDTO(mecanico);
    }

    @Override
    public MecanicoDTO getMecanicoById(Long id) throws EntityNotFoundException {
        Optional<Mecanico> mecanicoOpt = repository.findById(id);

        if(mecanicoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el mecanico");
        }

        return mapper.mapToDTO(mecanicoOpt.get());
    }

    @Override
    public Boolean deleteMecanico(Long id) throws EntityNotFoundException {
        Optional<Mecanico> mecanicoOpt = repository.findById(id);

        if(mecanicoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el mecanico");
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<MecanicoDTO> getAllMecanico() {
        return mapper.listToDTO(repository.findAll());
    }
}
