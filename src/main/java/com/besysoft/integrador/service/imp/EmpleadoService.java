package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.Empleado;
import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.re.EmpleadoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IEmpleadoMapper;
import com.besysoft.integrador.repository.EmpleadoRepository;
import com.besysoft.integrador.service.IEmpleadoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpleadoService implements IEmpleadoService {

    private EmpleadoRepository repository;
    private IEmpleadoMapper mapper;

    @Override
    public EmpleadoDTO createEmpleado(EmpleadoRE empleadoRE) {

        Empleado empleado = mapper.mapToEntity(empleadoRE);
        repository.save(empleado);
        EmpleadoDTO response = mapper.mapToDTO(empleado);

        return response;
    }

    @Override
    public EmpleadoDTO updateEmpleado(Long id, EmpleadoRE empleadoRE) throws EntityNotFoundException {
        Optional<Empleado> empleadoOpt = repository.findById(id);

        if(empleadoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el empleado");
        }
        Empleado empleado = empleadoOpt.get();

        if(empleadoRE.getApellido() != null){
            empleado.setApellido(empleadoRE.getApellido());
        }
        if(empleadoRE.getCelular() != null){
            empleado.setCelular(empleadoRE.getCelular());
        }
        if(empleadoRE.getCalle() != null){
            empleado.setCalle(empleadoRE.getCalle());
        }
        if(empleadoRE.getCodigoPostal() != null){
            empleado.setCodigoPostal(empleadoRE.getCodigoPostal());
        }
        if(empleadoRE.getDepartamento() != null){
            empleado.setDepartamento(empleadoRE.getDepartamento());
        }
        if(empleadoRE.getLocalidad() != null){
            empleado.setLocalidad(empleadoRE.getLocalidad());
        }
        if(empleadoRE.getNumero() != null){
            empleado.setNumero(empleadoRE.getNumero());
        }
        if(empleadoRE.getPiso() != null){
            empleado.setPiso(empleadoRE.getPiso());
        }
        if(empleadoRE.getNombre() != null){
            empleado.setNombre(empleadoRE.getNombre());
        }
        if(empleadoRE.getTipoEmpleado() != null){
            empleado.setTipoEmpleado(empleadoRE.getTipoEmpleado());
        }

        repository.save(empleado);

        return mapper.mapToDTO(empleado);
    }

    @Override
    public EmpleadoDTO getEmpleadoById(Long id) throws EntityNotFoundException {

        Optional<Empleado> empleado = repository.findById(id);

        if(empleado.isEmpty()){
            throw new EntityNotFoundException("No se encontro el empleado");
        }
        return mapper.mapToDTO(empleado.get());
    }

    @Override
    public Boolean deleteEmpleado(Long id) throws EntityNotFoundException {
        Optional<Empleado> empleado = repository.findById(id);

        if(empleado.isEmpty()){
            throw new EntityNotFoundException("No se encontro el empleado");
        }

        repository.deleteById(id);

        return true;
    }

    @Override
    public List<EmpleadoDTO> getAllEmpleado() {

        List<Empleado> list = repository.findAll();

        return mapper.listToDTO(list);
    }
}
