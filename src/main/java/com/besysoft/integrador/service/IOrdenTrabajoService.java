package com.besysoft.integrador.service;

import com.besysoft.integrador.dto.dto.OrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.OrdenTrabajoRE;
import com.besysoft.integrador.dto.re.PagoRE;
import com.besysoft.integrador.exceptions.BadStatusException;
import com.besysoft.integrador.exceptions.EntityNotFoundException;

import java.util.List;

public interface IOrdenTrabajoService {

    OrdenTrabajoDTO createOrdenTrabajo(OrdenTrabajoRE ordenTrabajoRE) throws EntityNotFoundException;

    OrdenTrabajoDTO getOrdenTrabajoById(Long id) throws EntityNotFoundException;

    Boolean deleteOrdenTrabajo(Long id) throws EntityNotFoundException;

    List<OrdenTrabajoDTO> getAllOrdenTrabajo();

    OrdenTrabajoDTO iniciarReparacion(Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException;

    OrdenTrabajoDTO finalizarReparacion(Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException;

    OrdenTrabajoDTO generarFacturacionyPago(Long ordenTrabajoId, Long administrativoId, PagoRE pago) throws EntityNotFoundException, BadStatusException;

    OrdenTrabajoDTO entregaVehiculo(Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException;

}
