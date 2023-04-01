package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.re.ClienteRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;

import java.util.List;

public interface IClienteService {

    ClienteDTO createClient(ClienteRE clienteRE) throws EntityNotFoundException, InvalidFieldException;

    ClienteDTO updateClient(Long id, ClienteRE clienteRE) throws EntityNotFoundException;

    ClienteDTO getClienteById(Long id) throws EntityNotFoundException;

    Boolean deleteClient(Long id) throws EntityNotFoundException;

    List<ClienteDTO> getAllClient();

    ClienteDTO buscarPorEmail(String email) throws EntityNotFoundException;

    ClienteDTO vincularVehiculo(Long clienteId, String patente) throws EntityNotFoundException;


}
