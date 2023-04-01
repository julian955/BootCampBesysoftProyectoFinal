package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.dto.dto.OrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.OrdenTrabajoRE;
import com.besysoft.integrador.dto.re.PagoRE;
import com.besysoft.integrador.exceptions.BadStatusException;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IOrdenTrabajoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdenTrabajoServiceTest {

    @Autowired
    private IOrdenTrabajoService service;

    private OrdenTrabajoRE ordenTrabajoRE = new OrdenTrabajoRE(
            6,
            "falla",
            "estado",
            null,
            null,
            null,
            "tarjeta",
            10505.54,
            101502L,
            "lleno",
            "mastercard",
            3L,
            4L,
            1L);

    @Test
    void createOrdenTrabajo() throws EntityNotFoundException {

        OrdenTrabajoDTO response = service.createOrdenTrabajo(ordenTrabajoRE);

        assertThat(response).isNotNull();
        assertThat(response.getFalla()).isEqualTo(ordenTrabajoRE.getFalla());
        assertThat(response.getFormaPago()).isEqualTo(ordenTrabajoRE.getFormaPago());
    }

    @Test
    void getOrdenTrabajoById() throws EntityNotFoundException {

        OrdenTrabajoDTO response = service.getOrdenTrabajoById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getAdministrativoDTO().getId()).isEqualTo(1L);
        assertThat(response.getRecepcionistaDTO().getId()).isEqualTo(2L);
        assertThat(response.getVehiculoDTO().getId()).isEqualTo(1L);
    }

    @Test
    void deleteOrdenTrabajo() throws EntityNotFoundException {

        OrdenTrabajoDTO ordenTrabajo = service.createOrdenTrabajo(ordenTrabajoRE);

        Boolean response = service.deleteOrdenTrabajo(6L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllOrdenTrabajo() {

        List<OrdenTrabajoDTO> responseList = service.getAllOrdenTrabajo();

        assertThat(responseList).isNotNull();
    }

    @Test
    void iniciarReparacion() throws BadStatusException, EntityNotFoundException {

        OrdenTrabajoDTO response = service.iniciarReparacion(1L);

        assertThat(response).isNotNull();
        assertThat(response.getEstado()).isEqualTo("En reparacion");
    }

    @Test
    void finalizarReparacion() throws BadStatusException, EntityNotFoundException {

        OrdenTrabajoDTO response = service.finalizarReparacion(2L);

        assertThat(response).isNotNull();
        assertThat(response.getEstado()).isEqualTo("Para facturar");
    }

    @Test
    void generarFacturacionyPago() throws BadStatusException, EntityNotFoundException {

        PagoRE pago = new PagoRE("efectivo",null,null);

        OrdenTrabajoDTO response = service.generarFacturacionyPago(3L,1L,pago);

        assertThat(response).isNotNull();
        assertThat(response.getEstado()).isEqualTo("Facturada");
        assertThat(response.getFormaPago()).isEqualTo("efectivo");

    }

    @Test
    void entregaVehiculo() throws BadStatusException, EntityNotFoundException {

        OrdenTrabajoDTO response = service.entregaVehiculo(4L);

        assertThat(response).isNotNull();
        assertThat(response.getEstado()).isEqualTo("Cerrada");
    }
}