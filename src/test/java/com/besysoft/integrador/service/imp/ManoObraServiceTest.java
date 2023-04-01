package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.dto.dto.ManoObraDTO;
import com.besysoft.integrador.dto.re.ManoObraRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IManoObraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManoObraServiceTest {

    @Autowired
    private IManoObraService service;

    private ManoObraRE manoObraRE = new ManoObraRE(
            "detalle",
            null,
            1L,
            1L);

    @Test
    void createManoObra() throws EntityNotFoundException {

        ManoObraDTO response = service.createManoObra(manoObraRE);

        assertThat(response).isNotNull();
        assertThat(response.getDetalle()).isEqualTo("detalle");
    }

    @Test
    void updateManoObra() throws EntityNotFoundException {

        ManoObraDTO response = service.updateManoObra(1L,manoObraRE);

        assertThat(response).isNotNull();
        assertThat(response.getDetalle()).isEqualTo("detalle");
    }

    @Test
    void getManoObraById() throws EntityNotFoundException {

        ManoObraDTO response = service.getManoObraById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getDetalle()).isEqualTo("detalle1");
    }

    @Test
    void deleteManoObra() throws EntityNotFoundException {

        Boolean response = service.deleteManoObra(1L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllManoObra() {

        List<ManoObraDTO> responseList = service.getAllManoObra();

        assertThat(responseList).isNotNull();
    }

    @Test
    void asignarMecanico() throws EntityNotFoundException {

        ManoObraDTO response = service.asignarMecanico(5L,1L);

        assertThat(response).isNotNull();
        assertThat(response.getMecanicoDTO().getId()).isEqualTo(1L);

    }
}