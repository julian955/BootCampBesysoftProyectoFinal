package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.Cliente;
import com.besysoft.integrador.domain.Vehiculo;
import com.besysoft.integrador.dto.dto.VehiculoDTO;
import com.besysoft.integrador.dto.re.VehiculoRE;
import com.besysoft.integrador.mapper.IClienteMapper;
import com.besysoft.integrador.mapper.IVehiculoMapper;
import com.besysoft.integrador.repository.ClienteRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VehiculoMapper implements IVehiculoMapper {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    @Lazy
    private IClienteMapper clienteMapper;

    @Override
    public Vehiculo mapToEntity(VehiculoRE vehiculoRE) {
        return Vehiculo.builder()
                .anio(vehiculoRE.getAnio())
                .color(vehiculoRE.getColor())
                .marca(vehiculoRE.getMarca())
                .modelo(vehiculoRE.getModelo())
                .patente(vehiculoRE.getPatente())
                .clientes(this.listarClientes(vehiculoRE.getClientesId()))
                .build();
    }

    @Override
    public VehiculoDTO mapToDTO(Vehiculo vehiculo) {
        return VehiculoDTO.builder()
                .id(vehiculo.getId())
                .anio(vehiculo.getAnio())
                .color(vehiculo.getColor())
                .marca(vehiculo.getMarca())
                .modelo(vehiculo.getModelo())
                .patente(vehiculo.getPatente())
                .clientesDTO(clienteMapper.listToBasicDTO(vehiculo.getClientes()))
                .build();
    }

    @Override
    public VehiculoDTO mapToBasicDTO(Vehiculo vehiculo) {
        return VehiculoDTO.builder()
                .id(vehiculo.getId())
                .marca(vehiculo.getMarca())
                .modelo(vehiculo.getModelo())
                .patente(vehiculo.getPatente())
                .build();
    }

    @Override
    public List<VehiculoDTO> listToDTO(List<Vehiculo> list) {

        if (list == null){
            return null;
        }

        return list
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehiculoDTO> listToBasicDTO(List<Vehiculo> list) {

        if (list == null){
            return null;
        }

        return list
                .stream()
                .map(this::mapToBasicDTO)
                .collect(Collectors.toList());
    }

    private Cliente validarCliente(Long id){

        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()){
            return clienteOpt.get();
        }
        return null;

    }

    private List<Cliente> listarClientes(List<Long> clientesId){

        if (clientesId == null){
            return null;
        }

        List<Cliente> lista = clientesId
                .stream()
                .map(this::validarCliente)
                .collect(Collectors.toList());

        lista.removeIf(Objects::isNull);

        if (lista == null){
            return null;
        }
        return lista;
    }
}
