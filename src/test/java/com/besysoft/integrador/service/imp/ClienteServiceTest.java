package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.re.ClienteRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;
import com.besysoft.integrador.service.IClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class ClienteServiceTest {

    @Autowired
    private IClienteService service;
    private ClienteRE cliente = new ClienteRE(
            "Perez",
            "2615123456",
            "Av. Corrientes",
            "5521",
            "Villa Nueva",
            "Guaymallen",
            "866",
            null,
            "perez.jorge@hotmail.com",
            "jorge",
            "4263215",
            null);

    @Test
    void createClient() throws EntityNotFoundException, InvalidFieldException {


       ClienteDTO response = service.createClient(cliente);


        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(cliente.getEmail());
        assertThat(response.getNombre()).isEqualTo(cliente.getNombre());
        assertThat(response.getApellido()).isEqualTo(cliente.getApellido());

    }

    @Test
    void updateClient() throws EntityNotFoundException {

        ClienteDTO response = service.updateClient(2L,cliente);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo(cliente.getNombre());
        assertThat(response.getApellido()).isEqualTo(cliente.getApellido());

    }

    @Test
    void getClienteById() throws EntityNotFoundException {

        ClienteDTO response = service.getClienteById(2L);

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("cliente2");
        assertThat(response.getApellido()).isEqualTo("apellido2");
        assertThat(response.getEmail()).isEqualTo("cliente2_mail@test.com");

    }

    @Test
    void deleteClient() throws EntityNotFoundException {

       Boolean response = service.deleteClient(2L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllClient() {

        List<ClienteDTO> list = service.getAllClient();

        assertThat(list).isNotNull();
    }

    @Test
    void buscarPorEmail() throws EntityNotFoundException {

        ClienteDTO response = service.buscarPorEmail("cliente2_mail@test.com");

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("cliente2");
        assertThat(response.getApellido()).isEqualTo("apellido2");
        assertThat(response.getEmail()).isEqualTo("cliente2_mail@test.com");
    }

    @Test
    void vincularVehiculo() throws EntityNotFoundException {

        ClienteDTO response = service.vincularVehiculo(3L,"patente4");

        assertThat(response).isNotNull();
        assertThat(response.getNombre()).isEqualTo("cliente3");
        assertThat(response.getApellido()).isEqualTo("apellido3");
        assertThat(response.getEmail()).isEqualTo("cliente3_mail@test.com");
        assertThat(response.getVehiculosDTO()).isNotNull();
        assertThat(response.getVehiculosDTO().size()).isEqualTo(2);
    }
}