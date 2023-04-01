package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.ManoObra;
import com.besysoft.integrador.domain.Mecanico;
import com.besysoft.integrador.dto.dto.ManoObraDTO;
import com.besysoft.integrador.dto.re.ManoObraRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IManoObraMapper;
import com.besysoft.integrador.repository.ManoObraRepository;
import com.besysoft.integrador.repository.MecanicoRepository;
import com.besysoft.integrador.service.IManoObraService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManoObraService implements IManoObraService {

    private IManoObraMapper mapper;
    private ManoObraRepository repository;
    private MecanicoRepository mecanicoRepository;

    @Override
    public ManoObraDTO createManoObra(ManoObraRE manoObraRE) throws EntityNotFoundException {

        ManoObra manoObra = mapper.mapToEntity(manoObraRE);

        repository.save(manoObra);

        return mapper.mapToDTO(manoObra);
    }

    @Override
    public ManoObraDTO updateManoObra(Long id, ManoObraRE manoObraRE) throws EntityNotFoundException {

        Optional<ManoObra> manoObraOpt = repository.findById(id);

        if(manoObraOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la mano de obra");
        }

        ManoObra  manoObra = manoObraOpt.get();

        if(manoObraRE.getDetalle() != null){
            manoObra.setDetalle(manoObraRE.getDetalle());
        }
        if(manoObraRE.getDuracion() != null){
            manoObra.setDuracion(manoObraRE.getDuracion());
        }

        repository.save(manoObra);

        return mapper.mapToDTO(manoObra);

    }

    @Override
    public ManoObraDTO getManoObraById(Long id) throws EntityNotFoundException {

        Optional<ManoObra> manoObra = repository.findById(id);

        if(manoObra.isEmpty()){
            throw new EntityNotFoundException("No se encontro la mano de obra");
        }

        return mapper.mapToDTO(manoObra.get());
    }

    @Override
    public Boolean deleteManoObra(Long id) throws EntityNotFoundException {

        Optional<ManoObra> manoObra = repository.findById(id);

        if(manoObra.isEmpty()){
            throw new EntityNotFoundException("No se encontro la mano de obra");
        }

        repository.deleteById(id);

        return true;
    }

    @Override
    public List<ManoObraDTO> getAllManoObra() {
        return mapper.listToDTO(repository.findAll());
    }

    @Override
    public ManoObraDTO asignarMecanico(Long manoObraId, Long mecanicoId) throws EntityNotFoundException {

        Optional<ManoObra> manoObraOpt = repository.findById(manoObraId);

        if(manoObraOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la mano de obra");
        }

        ManoObra manoObra = manoObraOpt.get();

        Optional<Mecanico> mecanicoOpt = mecanicoRepository.findById(mecanicoId);

        if(mecanicoOpt.isEmpty()) {
            throw new EntityNotFoundException("No se encontro el mecanico");
        }

        Mecanico mecanico = mecanicoOpt.get();

        manoObra.setMecanico(mecanico);
        repository.save(manoObra);

        return mapper.mapToDTO(manoObra);
    }
}
