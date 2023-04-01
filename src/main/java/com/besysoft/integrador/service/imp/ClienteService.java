package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.Cliente;
import com.besysoft.integrador.domain.Vehiculo;
import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.re.ClienteRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;
import com.besysoft.integrador.mapper.IClienteMapper;
import com.besysoft.integrador.repository.ClienteRepository;
import com.besysoft.integrador.repository.VehiculoRepository;
import com.besysoft.integrador.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;


@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private IClienteMapper mapper;


    @Override
    public ClienteDTO createClient(ClienteRE clienteRE) throws EntityNotFoundException, InvalidFieldException {

        this.validarEmail(clienteRE.getEmail());

        Cliente entity = mapper.mapToEntity(clienteRE);

        repository.save(entity);

        ClienteDTO response = mapper.mapToDTO(entity);

        return response;
    }

    @Override
    public ClienteDTO updateClient(Long id, ClienteRE clienteRE) throws EntityNotFoundException {

        Optional<Cliente> clienteOpt = repository.findById(id);

        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente");
        }

        Cliente entity = clienteOpt.get();

        if(clienteRE.getApellido() != null){
            entity.setApellido(clienteRE.getApellido());
        }
        if(clienteRE.getCelular() != null){
            entity.setCelular(clienteRE.getCelular());
        }
        if(clienteRE.getCalle() != null){
            entity.setCalle(clienteRE.getCalle());
        }
        if(clienteRE.getCodigoPostal() != null){
            entity.setCodigoPostal(clienteRE.getCodigoPostal());
        }
        if(clienteRE.getDepartamento() != null){
            entity.setDepartamento(clienteRE.getDepartamento());
        }
        if(clienteRE.getLocalidad() != null){
            entity.setLocalidad(clienteRE.getLocalidad());
        }
        if(clienteRE.getNumero() != null){
            entity.setNumero(clienteRE.getNumero());
        }
        if(clienteRE.getPiso() != null){
            entity.setPiso(clienteRE.getPiso());
        }
        if(clienteRE.getNombre() != null){
            entity.setNombre(clienteRE.getNombre());
        }
        if(clienteRE.getTelefono() != null){
            entity.setTelefono(clienteRE.getTelefono());
        }

        repository.save(entity);

        return mapper.mapToDTO(entity);
    }

    @Override
    public ClienteDTO getClienteById(Long id) throws EntityNotFoundException {

        Optional<Cliente> clienteOpt = repository.findById(id);

        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente");
        }

        return mapper.mapToDTO(clienteOpt.get());
    }

    @Override
    public Boolean deleteClient(Long id) throws EntityNotFoundException {

        Optional<Cliente> clienteOpt = repository.findById(id);

        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente");
        }

        repository.deleteById(id);

        return true;

    }

    @Override
    public List<ClienteDTO> getAllClient() {

        List<Cliente> clienteList = repository.findAll();

        return mapper.listToDTO(clienteList);
    }

    @Override
    public ClienteDTO buscarPorEmail(String email) throws EntityNotFoundException {

        Optional<Cliente> cliente = repository.findByEmail(email);

        if(cliente.isEmpty()){
            throw new EntityNotFoundException("El cliente buscado no existe");
        }
        return mapper.mapToDTO(cliente.get());
    }

    @Override
    public ClienteDTO vincularVehiculo(Long clienteId, String patente) throws EntityNotFoundException {

        Optional<Cliente> clienteOpt = repository.findById(clienteId);

        if(clienteOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el cliente");
        }

        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findByPatente(patente);

        if(vehiculoOpt.isEmpty()){
            throw new EntityNotFoundException("El vehiculo buscado no existe.");
        }

        Cliente cliente = clienteOpt.get();
        Vehiculo vehiculo = vehiculoOpt.get();

        cliente.getVehiculos().add(vehiculo);
        repository.save(cliente);
        ClienteDTO response = mapper.mapToDTO(cliente);
        return response;
    }

    public void validarEmail(String email) throws EntityNotFoundException, InvalidFieldException {

        Optional<Cliente> cliente = repository.findByEmail(email);

        if(cliente.isPresent()){
            throw new InvalidFieldException("El email ingresado ya existe.");
        }
    }


}
