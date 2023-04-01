package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.Cliente;
import com.besysoft.integrador.domain.Vehiculo;
import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.re.ClienteRE;
import com.besysoft.integrador.mapper.IClienteMapper;
import com.besysoft.integrador.mapper.IVehiculoMapper;
import com.besysoft.integrador.repository.VehiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ClienteMapper implements IClienteMapper {

    private VehiculoRepository vehiculoRepository;
    private IVehiculoMapper vehiculoMapper;

    @Override
    public Cliente mapToEntity(ClienteRE clienteRE) {
        return Cliente.builder()
                .apellido(clienteRE.getApellido())
                .celular(clienteRE.getCelular())
                .calle(clienteRE.getCalle())
                .codigoPostal(clienteRE.getCodigoPostal())
                .departamento(clienteRE.getDepartamento())
                .localidad(clienteRE.getLocalidad())
                .numero(clienteRE.getNumero())
                .piso(clienteRE.getPiso())
                .email(clienteRE.getEmail())
                .nombre(clienteRE.getNombre())
                .telefono(clienteRE.getTelefono())
                .vehiculos(this.listarVehiculos(clienteRE.getVehiculosId()))
                .build();
    }

    @Override
    public ClienteDTO mapToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .apellido(cliente.getApellido())
                .celular(cliente.getCelular())
                .calle(cliente.getCalle())
                .codigoPostal(cliente.getCodigoPostal())
                .departamento(cliente.getDepartamento())
                .localidad(cliente.getLocalidad())
                .numero(cliente.getNumero())
                .piso(cliente.getPiso())
                .email(cliente.getEmail())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .vehiculosDTO(vehiculoMapper.listToBasicDTO(cliente.getVehiculos()))
                .build();
    }

    @Override
    public ClienteDTO mapToBasicDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .build();
    }

    @Override
    public List<ClienteDTO> listToDTO(List<Cliente> list) {

        if (list == null){
            return null;
        }

        return list
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDTO> listToBasicDTO(List<Cliente> list) {

        if (list == null){
            return null;
        }

        return list
                .stream()
                .map(this::mapToBasicDTO)
                .collect(Collectors.toList());
    }

    private Vehiculo validarVehiculo(Long id){

        Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(id);

        if(vehiculoOpt.isPresent()){
            return vehiculoOpt.get();
        }

        return null;
    }

    private List<Vehiculo> listarVehiculos(List<Long> vehiculosId){

        if (vehiculosId == null){
            return null;
        }

        List<Vehiculo> lista = vehiculosId
                .stream()
                .map(this::validarVehiculo)
                .collect(Collectors.toList());

        lista.removeIf(Objects::isNull);

        return lista;
    }
}
