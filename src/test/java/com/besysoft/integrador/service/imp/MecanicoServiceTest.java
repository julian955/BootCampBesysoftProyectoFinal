package com.besysoft.integrador.service.imp;


import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.MecanicoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IMecanicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MecanicoServiceTest {

    @Autowired
    private IMecanicoService service;

    private MecanicoRE mecanicoRE = new MecanicoRE(
            's',
            "Perez",
            "2615123456",
            "Av. Corrientes",
            "5521",
            "Villa Nueva",
            "Guaymallen",
            "866",
            null,
            "electricista",
            "jorge");

    @Test
    void createMecanico() {

        MecanicoDTO response = service.createMecanico(mecanicoRE);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo(mecanicoRE.getNombre());
        assertThat(response.getApellido()).isEqualTo(mecanicoRE.getApellido());
    }

    @Test
    void updateMecanico() throws EntityNotFoundException {

        MecanicoDTO response = service.updateMecanico(2L,mecanicoRE);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo(mecanicoRE.getNombre());
        assertThat(response.getApellido()).isEqualTo(mecanicoRE.getApellido());
    }

    @Test
    void getMecanicoById() throws EntityNotFoundException {

        MecanicoDTO response = service.getMecanicoById(2L);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("mecanico2");
        assertThat(response.getApellido()).isEqualTo("apellido");
    }

    @Test
    void deleteMecanico() throws EntityNotFoundException {

        MecanicoDTO mecanico = service.createMecanico(mecanicoRE);
        Boolean response = service.deleteMecanico(7L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllMecanico() {

        List<MecanicoDTO> responseList = service.getAllMecanico();

        assertThat(responseList).isNotNull();
    }
}