package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.ManoObra;
import com.besysoft.integrador.domain.Mecanico;
import com.besysoft.integrador.domain.OrdenTrabajo;
import com.besysoft.integrador.dto.dto.ManoObraDTO;
import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.ManoObraRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IManoObraMapper;
import com.besysoft.integrador.mapper.IMecanicoMapper;
import com.besysoft.integrador.mapper.IOrdenTrabajoMapper;
import com.besysoft.integrador.repository.MecanicoRepository;
import com.besysoft.integrador.repository.OrdenTrabajoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ManoObraMapper implements IManoObraMapper {


    private IMecanicoMapper mecanicoMapper;
    private IOrdenTrabajoMapper ordenTrabajoMapper;
    private MecanicoRepository mecanicoRepository;
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Override
    public ManoObra mapToEntity(ManoObraRE manoObraRE) throws EntityNotFoundException {
        return ManoObra.builder()
                .detalle(manoObraRE.getDetalle())
                .duracion(manoObraRE.getDuracion())
                .mecanico(this.validarMecanico(manoObraRE.getMecanicoId()))
                .ordenTrabajo(this.validarOrdenTrabajo(manoObraRE.getOrdenTrabajoId()))
                .build();
    }

    @Override
    public ManoObraDTO mapToDTO(ManoObra manoObra) {

        MecanicoDTO mecanico = null;

        if (manoObra.getMecanico() != null){
            mecanico = mecanicoMapper.mapToBasicDTO(manoObra.getMecanico());
        }

        return ManoObraDTO.builder()
                .id(manoObra.getId())
                .detalle(manoObra.getDetalle())
                .duracion(manoObra.getDuracion())
                .mecanicoDTO(mecanico)
                .ordenTrabajoDTO(ordenTrabajoMapper.mapToBasicDTO(manoObra.getOrdenTrabajo()))
                .build();
    }

    @Override
    public List<ManoObraDTO> listToDTO(List<ManoObra> list) {
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Mecanico validarMecanico(Long id) throws EntityNotFoundException {

        if (id == null){
            return null;
        }

        Optional<Mecanico> mecanico = mecanicoRepository.findById(id);

        if(mecanico.isEmpty()){
            throw new EntityNotFoundException("No se encontro el mecanico");
        }

        return mecanico.get();
    }

    private OrdenTrabajo validarOrdenTrabajo(Long id){
        Optional<OrdenTrabajo> ordenTrabajo = ordenTrabajoRepository.findById(id);

        if(ordenTrabajo.isPresent()){
            return ordenTrabajo.get();
        }
        return null;
    }
}
