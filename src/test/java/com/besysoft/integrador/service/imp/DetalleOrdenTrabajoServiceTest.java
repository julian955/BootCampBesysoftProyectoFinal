package com.besysoft.integrador.service.imp;


import com.besysoft.integrador.dto.dto.DetalleOrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.DetalleOrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IDetalleOrdenTrabajoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DetalleOrdenTrabajoServiceTest {

    @Autowired
    private IDetalleOrdenTrabajoService service;
    private DetalleOrdenTrabajoRE detalleOT = new DetalleOrdenTrabajoRE(
            2,
            1000.50,
            1L,
            1L);

    @Test
    void createDetalle() throws EntityNotFoundException {

        DetalleOrdenTrabajoDTO response = service.createDetalle(detalleOT);

        assertThat(response).isNotNull();
        assertThat(response.getCantidad()).isEqualTo(detalleOT.getCantidad());
        assertThat(response.getTotal()).isEqualTo(detalleOT.getTotal());
    }

    @Test
    void updateDetalle() throws EntityNotFoundException {

        DetalleOrdenTrabajoDTO response = service.updateDetalle(2L,detalleOT);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(2L);
        assertThat(response.getCantidad()).isEqualTo(detalleOT.getCantidad());
        assertThat(response.getTotal()).isEqualTo(detalleOT.getTotal());
    }

    @Test
    void getDetalleById() throws EntityNotFoundException {

        DetalleOrdenTrabajoDTO response = service.getDetalleById(2L);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(2L);
        assertThat(response.getCantidad()).isEqualTo(2);
        assertThat(response.getTotal()).isEqualTo(200.50);
    }

    @Test
    void deleteDetalle() throws EntityNotFoundException {

        Boolean response = service.deleteDetalle(2L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllDetalle() {

        List<DetalleOrdenTrabajoDTO> list = service.getAllDetalle();

        assertThat(list).isNotNull();
    }
}