package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.re.EmpleadoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IEmpleadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmpleadoServiceTest {

    @Autowired
    private IEmpleadoService service;
    private EmpleadoRE empleadoRE=new EmpleadoRE(
            "Perez",
            "2615123456",
            "Av. Corrientes",
            "5521",
            "Villa Nueva",
            "Guaymallen",
            "866",
            null,
            "jorge",
            "administrativo");

    @Test
    void createEmpleado() {

        EmpleadoDTO response = service.createEmpleado(empleadoRE);

        assertThat(response).isNotNull();
        assertThat(response.getTipoEmpleado()).isEqualTo(empleadoRE.getTipoEmpleado());
        assertThat(response.getNombre()).isEqualTo(empleadoRE.getNombre());
        assertThat(response.getApellido()).isEqualTo(empleadoRE.getApellido());
    }

    @Test
    void updateEmpleado() throws EntityNotFoundException {

        EmpleadoDTO response = service.updateEmpleado(2L,empleadoRE);

        assertThat(response).isNotNull();
        assertThat(response.getTipoEmpleado()).isEqualTo(empleadoRE.getTipoEmpleado());
        assertThat(response.getNombre()).isEqualTo(empleadoRE.getNombre());
        assertThat(response.getApellido()).isEqualTo(empleadoRE.getApellido());
    }

    @Test
    void getEmpleadoById() throws EntityNotFoundException {

        EmpleadoDTO response = service.getEmpleadoById(2L);

        assertThat(response).isNotNull();
        assertThat(response.getTipoEmpleado()).isEqualTo("administrativo");
        assertThat(response.getNombre()).isEqualTo("empleado2");
        assertThat(response.getApellido()).isEqualTo("apellido");
    }

    @Test
    void deleteEmpleado() throws EntityNotFoundException {

        EmpleadoDTO empleado = service.createEmpleado(empleadoRE);

        Boolean response = service.deleteEmpleado(7L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllEmpleado() {

        List<EmpleadoDTO> list = service.getAllEmpleado();

        assertThat(list).isNotNull();
    }
}