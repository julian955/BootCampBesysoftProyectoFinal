package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.VehiculoDTO;
import com.besysoft.integrador.dto.re.VehiculoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;

import java.util.List;

public interface IVehiculoService {

    VehiculoDTO createVehiculo(VehiculoRE vehiculoRE) throws InvalidFieldException;

    VehiculoDTO updateVehiculo(Long id, VehiculoRE vehiculoRE) throws EntityNotFoundException;

    VehiculoDTO getVehiculoById(Long id ) throws EntityNotFoundException;

    Boolean deleteVehiculo(Long id) throws EntityNotFoundException;

    List<VehiculoDTO> getAllVehiculo();

    VehiculoDTO getVehiculoByPatente(String patente) throws EntityNotFoundException;
}
