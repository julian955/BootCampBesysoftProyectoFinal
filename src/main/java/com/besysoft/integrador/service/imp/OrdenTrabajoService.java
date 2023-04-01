package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.domain.DetalleOrdenTrabajo;
import com.besysoft.integrador.domain.Empleado;
import com.besysoft.integrador.domain.OrdenTrabajo;
import com.besysoft.integrador.dto.dto.OrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.OrdenTrabajoRE;
import com.besysoft.integrador.dto.re.PagoRE;
import com.besysoft.integrador.exceptions.BadStatusException;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.mapper.IOrdenTrabajoMapper;
import com.besysoft.integrador.repository.DetalleOrdenTrabajoRepository;
import com.besysoft.integrador.repository.EmpleadoRepository;
import com.besysoft.integrador.repository.MecanicoRepository;
import com.besysoft.integrador.repository.OrdenTrabajoRepository;
import com.besysoft.integrador.service.IOrdenTrabajoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdenTrabajoService implements IOrdenTrabajoService {

    private OrdenTrabajoRepository repository;
    private IOrdenTrabajoMapper mapper;
    private MecanicoRepository mecanicoRepository;
    private DetalleOrdenTrabajoRepository detalleOrdenTrabajoRepository;
    private EmpleadoRepository empleadoRepository;

    @Override
    public OrdenTrabajoDTO createOrdenTrabajo(OrdenTrabajoRE ordenTrabajoRE) throws EntityNotFoundException {

        OrdenTrabajo ordenTrabajo = mapper.mapToEntity(ordenTrabajoRE);
        ordenTrabajo.setIngreso(LocalDate.now());
        ordenTrabajo.setEstado("Creada");

        repository.save(ordenTrabajo);

        return mapper.mapToDTO(ordenTrabajo);
    }

    @Override
    public OrdenTrabajoDTO getOrdenTrabajoById(Long id) throws EntityNotFoundException {

        Optional<OrdenTrabajo> ordenTrabajoOpt = repository.findById(id);

        if(ordenTrabajoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo.");
        }

        return mapper.mapToDTO(ordenTrabajoOpt.get());
    }

    @Override
    public Boolean deleteOrdenTrabajo(Long id) throws EntityNotFoundException {
        Optional<OrdenTrabajo> ordenTrabajoOpt = repository.findById(id);

        if(ordenTrabajoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo.");
        }
        repository.deleteById(id);

        return true;
    }

    @Override
    public List<OrdenTrabajoDTO> getAllOrdenTrabajo() {
        return mapper.listToDTO(repository.findAll());
    }

    public OrdenTrabajoDTO iniciarReparacion(Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException {
        Optional<OrdenTrabajo> ordenTrabajoOpt = repository.findById(ordenTrabajoId);

        if(ordenTrabajoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo.");
        }
        OrdenTrabajo ordenTrabajo = ordenTrabajoOpt.get();

        if (!ordenTrabajo.getEstado().equalsIgnoreCase("Creada")){
            throw new BadStatusException("El estado de la orden de trabajo es incorrecta.");
        }

        ordenTrabajo.setEstado("En reparacion");
        repository.save(ordenTrabajo);

        return mapper.mapToDTO(ordenTrabajo);
    }

    @Override
    public OrdenTrabajoDTO finalizarReparacion(Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException {
        Optional<OrdenTrabajo> ordenTrabajoOpt = repository.findById(ordenTrabajoId);

        if(ordenTrabajoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo.");
        }
        OrdenTrabajo ordenTrabajo = ordenTrabajoOpt.get();

        if (!ordenTrabajo.getEstado().equalsIgnoreCase("En reparacion")){
            throw new BadStatusException("El estado de la orden de trabajo es incorrecta.");
        }

        ordenTrabajo.setEstado("Para facturar");
        repository.save(ordenTrabajo);

        return mapper.mapToDTO(ordenTrabajo);
    }


    @Override
    public OrdenTrabajoDTO generarFacturacionyPago(Long ordenTrabajoId, Long administrativoId, PagoRE pago) throws EntityNotFoundException, BadStatusException {

        Optional<OrdenTrabajo> ordenTrabajoOpt = repository.findById(ordenTrabajoId);
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(administrativoId);

        if(ordenTrabajoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo.");
        }
        if(empleadoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro el empleado ingresado.");
        }
        OrdenTrabajo ordenTrabajo = ordenTrabajoOpt.get();
        Empleado empleado = empleadoOpt.get();

        if (!ordenTrabajo.getEstado().equalsIgnoreCase("Para facturar")){
            throw new BadStatusException("El estado de la orden de trabajo es incorrecta.");
        }
        if (!empleado.getTipoEmpleado().equalsIgnoreCase("administrativo")){
            throw new BadStatusException("El empleado ingresado no es administrativo.");
        }

        Double total = this.sacarTotal(ordenTrabajoId);


        ordenTrabajo.setImporte(total);
        ordenTrabajo.setAdministrativo(empleado);
        ordenTrabajo.setFormaPago(pago.getFormaPago());

        if (pago.getTipoTarjeta() != null){
            ordenTrabajo.setTipoTarjeta(pago.getTipoTarjeta());
        }
        if(pago.getCuotas() != null){
            ordenTrabajo.setCantidadCuota(pago.getCuotas());
        }

        ordenTrabajo.setEstado("Facturada");

        repository.save(ordenTrabajo);

        return mapper.mapToDTO(ordenTrabajo);
    }

    private Double sacarTotal(Long ordenTrabajoId){

        List<DetalleOrdenTrabajo> list1 = detalleOrdenTrabajoRepository.findAll();

        if (list1 == null){
            return null;
        }

        List<DetalleOrdenTrabajo> list2 =list1.stream()
                .filter(x -> Objects.equals(x.getOrdenTrabajo().getId(), ordenTrabajoId))
                .collect(Collectors.toList());

        if (list2 == null){
            return null;
        }

        Double total = list2.stream()
                .mapToDouble(DetalleOrdenTrabajo::getTotal)
                .sum();

        return total;
    }

    @Override
    public OrdenTrabajoDTO entregaVehiculo(Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException {
        Optional<OrdenTrabajo> ordenTrabajoOpt = repository.findById(ordenTrabajoId);

        if(ordenTrabajoOpt.isEmpty()){
            throw new EntityNotFoundException("No se encontro la orden de trabajo.");
        }
        OrdenTrabajo ordenTrabajo = ordenTrabajoOpt.get();

        if (!ordenTrabajo.getEstado().equalsIgnoreCase("Facturada")){
            throw new BadStatusException("El estado de la orden de trabajo es incorrecta.");
        }

        ordenTrabajo.setEstado("Cerrada");
        repository.save(ordenTrabajo);

        return mapper.mapToDTO(ordenTrabajo);
    }

}
