package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.Cliente;
import com.besysoft.integrador.domain.Vehiculo;
import com.besysoft.integrador.dto.dto.VehiculoDTO;
import com.besysoft.integrador.dto.re.VehiculoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;
import com.besysoft.integrador.mapper.IVehiculoMapper;
import com.besysoft.integrador.repository.ClienteRepository;
import com.besysoft.integrador.repository.VehiculoRepository;
import com.besysoft.integrador.service.IVehiculoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehiculoService implements IVehiculoService {


    private VehiculoRepository repository;
    private ClienteRepository clienteRepository;
    private IVehiculoMapper mapper;

    @Override
    public VehiculoDTO createVehiculo(VehiculoRE vehiculoRE) throws InvalidFieldException {

        this.validarPatente(vehiculoRE.getPatente());
        Vehiculo vehiculo = mapper.mapToEntity(vehiculoRE);
        repository.save(vehiculo);
        return mapper.mapToDTO(vehiculo);
    }

    @Override
    public VehiculoDTO updateVehiculo(Long id, VehiculoRE vehiculoRE) throws EntityNotFoundException {

        Optional<Vehiculo> vehiculoOpt = repository.findById(id);

        if(vehiculoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el vehiculo.");
        }

        Vehiculo vehiculo = vehiculoOpt.get();

        if(vehiculoRE.getAnio() != null){
            vehiculo.setAnio(vehiculoRE.getAnio());
        }
        if(vehiculoRE.getColor() != null){
            vehiculo.setColor(vehiculoRE.getColor());
        }
        if(vehiculoRE.getMarca() != null){
            vehiculo.setMarca(vehiculoRE.getMarca());
        }
        if(vehiculoRE.getModelo() != null){
            vehiculo.setModelo(vehiculoRE.getModelo());
        }

        repository.save(vehiculo);

        return mapper.mapToDTO(vehiculo);
    }

    @Override
    public VehiculoDTO getVehiculoById(Long id) throws EntityNotFoundException {

        Optional<Vehiculo> vehiculoOpt = repository.findById(id);

        if(vehiculoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el vehiculo.");
        }

        return mapper.mapToDTO(vehiculoOpt.get());
    }

    @Override
    public Boolean deleteVehiculo(Long id) throws EntityNotFoundException {

        Optional<Vehiculo> vehiculoOpt = repository.findById(id);

        if(vehiculoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el vehiculo.");
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public List<VehiculoDTO> getAllVehiculo() {
        return mapper.listToDTO(repository.findAll());
    }

    @Override
    public VehiculoDTO getVehiculoByPatente(String patente) throws EntityNotFoundException {

        Optional<Vehiculo> vehiculoOpt = repository.findByPatente(patente);

        if(vehiculoOpt.isEmpty()){
            throw new EntityNotFoundException("El vehiculo buscado no existe.");
        }
        return mapper.mapToDTO(vehiculoOpt.get());
    }

    public void validarPatente(String patente) throws InvalidFieldException {

        Optional<Vehiculo> vehiculoOpt = repository.findByPatente(patente);

        if(vehiculoOpt.isPresent()){
            throw new InvalidFieldException("La patente ingresada ya existe.");
        }

    }

}
