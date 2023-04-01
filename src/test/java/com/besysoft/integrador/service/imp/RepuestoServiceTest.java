package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.dto.dto.RepuestoDTO;
import com.besysoft.integrador.dto.re.RepuestoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IRepuestoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepuestoServiceTest {

    @Autowired
    private IRepuestoService service;

    private RepuestoRE repuestoRE = new RepuestoRE(
            "Bosch",
            "SK-16",
            "Bujias",
            500.0);

    @Test
    void createRepuesto() {

        RepuestoDTO response = service.createRepuesto(repuestoRE);

        assertThat(response).isNotNull();
        assertThat(response.getRepuesto()).isEqualTo("Bujias");
        assertThat(response.getMarca()).isEqualTo("Bosch");

    }

    @Test
    void updateRepuesto() throws EntityNotFoundException {

        RepuestoDTO response = service.updateRepuesto(1L,repuestoRE);

        assertThat(response).isNotNull();
        assertThat(response.getRepuesto()).isEqualTo("Bujias");
        assertThat(response.getMarca()).isEqualTo("Bosch");
    }

    @Test
    void getRepuestoById() throws EntityNotFoundException {

        RepuestoDTO response = service.getRepuestoById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getRepuesto()).isEqualTo("Bujia");
        assertThat(response.getMarca()).isEqualTo("Bosch");
    }

    @Test
    void deleteRepuesto() throws EntityNotFoundException {

        RepuestoDTO repuesto = service.createRepuesto(repuestoRE);

        Boolean response = service.deleteRepuesto(5L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllRepuesto() {

        List<RepuestoDTO> responseList = service.getAllRepuesto();

        assertThat(responseList).isNotNull();
    }
}