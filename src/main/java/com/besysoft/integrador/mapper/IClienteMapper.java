package com.besysoft.integrador.mapper;

import com.besysoft.integrador.domain.Cliente;
import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.re.ClienteRE;


import java.util.List;


public interface IClienteMapper {

    Cliente mapToEntity(ClienteRE clienteRE);

    ClienteDTO mapToDTO(Cliente cliente);

    ClienteDTO mapToBasicDTO(Cliente cliente);

    List<ClienteDTO> listToDTO(List<Cliente> list);

    List<ClienteDTO> listToBasicDTO(List<Cliente> list);

}
