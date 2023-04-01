package com.besysoft.integrador.mapper.imp;

import com.besysoft.integrador.domain.Empleado;
import com.besysoft.integrador.domain.OrdenTrabajo;
import com.besysoft.integrador.domain.Vehiculo;
import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.dto.OrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.OrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IEmpleadoMapper;
import com.besysoft.integrador.mapper.IOrdenTrabajoMapper;
import com.besysoft.integrador.mapper.IVehiculoMapper;
import com.besysoft.integrador.repository.EmpleadoRepository;
import com.besysoft.integrador.repository.VehiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrdenTrabajoMapper implements IOrdenTrabajoMapper {


    private IEmpleadoMapper empleadoMapper;
    private IVehiculoMapper vehiculoMapper;
    private EmpleadoRepository empleadoRepository;
    private VehiculoRepository vehiculoRepository;

    @Override
    public OrdenTrabajo mapToEntity(OrdenTrabajoRE ordenTrabajoRE) throws EntityNotFoundException {
        return OrdenTrabajo.builder()
                .cantidadCuota(ordenTrabajoRE.getCantidadCuota())
                .falla(ordenTrabajoRE.getFalla())
                .estado(ordenTrabajoRE.getEstado())
                .finReparacion(ordenTrabajoRE.getFinReparacion())
                .ingreso(ordenTrabajoRE.getIngreso())
                .fechaPago(ordenTrabajoRE.getFechaPago())
                .formaPago(ordenTrabajoRE.getFormaPago())
                .importe(ordenTrabajoRE.getImporte())
                .kilometraje(ordenTrabajoRE.getKilometraje())
                .nivelCombustible(ordenTrabajoRE.getNivelCombustible())
                .tipoTarjeta(ordenTrabajoRE.getTipoTarjeta())
                .administrativo(this.validarEmpleado(ordenTrabajoRE.getAdministrativoId()))
                .recepcionista(this.validarEmpleado(ordenTrabajoRE.getRecepcionistaId()))
                .vehiculo(this.validarVehiculo(ordenTrabajoRE.getVehiculoId()))
                .build();
    }

    @Override
    public OrdenTrabajoDTO mapToDTO(OrdenTrabajo ordenTrabajo) {

        EmpleadoDTO administrativo = null;
        EmpleadoDTO recepcionista = null;

        if (ordenTrabajo.getAdministrativo() != null){
          administrativo = empleadoMapper.mapToBasicDTO(ordenTrabajo.getAdministrativo());
        }
        if (ordenTrabajo.getRecepcionista() != null){
            recepcionista = empleadoMapper.mapToBasicDTO(ordenTrabajo.getRecepcionista());
        }

        return OrdenTrabajoDTO.builder()
                .id(ordenTrabajo.getId())
                .cantidadCuota(ordenTrabajo.getCantidadCuota())
                .falla(ordenTrabajo.getFalla())
                .estado(ordenTrabajo.getEstado())
                .finReparacion(ordenTrabajo.getFinReparacion())
                .ingreso(ordenTrabajo.getIngreso())
                .fechaPago(ordenTrabajo.getFechaPago())
                .formaPago(ordenTrabajo.getFormaPago())
                .importe(ordenTrabajo.getImporte())
                .kilometraje(ordenTrabajo.getKilometraje())
                .nivelCombustible(ordenTrabajo.getNivelCombustible())
                .tipoTarjeta(ordenTrabajo.getTipoTarjeta())
                .administrativoDTO(administrativo)
                .recepcionistaDTO(recepcionista)
                .vehiculoDTO(vehiculoMapper.mapToBasicDTO(ordenTrabajo.getVehiculo()))
                .build();
    }

    @Override
    public OrdenTrabajoDTO mapToBasicDTO(OrdenTrabajo ordenTrabajo) {
        return OrdenTrabajoDTO.builder()
                .id(ordenTrabajo.getId())
                .falla(ordenTrabajo.getFalla())
                .estado(ordenTrabajo.getEstado())
                .importe(ordenTrabajo.getImporte())
                .formaPago(ordenTrabajo.getFormaPago())
                .vehiculoDTO(vehiculoMapper.mapToBasicDTO(ordenTrabajo.getVehiculo()))
                .build();
    }

    @Override
    public List<OrdenTrabajoDTO> listToDTO(List<OrdenTrabajo> list) {
        return list.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private Empleado validarEmpleado(Long id) throws EntityNotFoundException {

        if (id == null){
            return null;
        }

        Optional<Empleado> empleado = empleadoRepository.findById(id);

        if(empleado.isEmpty()){
            throw new EntityNotFoundException("No se encontro el empleado.");
        }
        return empleado.get();
    }

    private Vehiculo validarVehiculo(Long id) throws EntityNotFoundException {

        if (id == null){
            return null;
        }

        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);

        if(vehiculo.isEmpty()){
            throw new EntityNotFoundException("No se encontro el vehiculo.");
        }
        return vehiculo.get();
    }
}
