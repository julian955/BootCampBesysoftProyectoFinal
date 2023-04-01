package com.besysoft.integrador.service.imp;

import com.besysoft.integrador.dto.dto.VehiculoDTO;
import com.besysoft.integrador.dto.re.VehiculoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;
import com.besysoft.integrador.service.IVehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VehiculoServiceTest {

    @Autowired
    private IVehiculoService service;

    private VehiculoRE vehiculoRE;

    @BeforeEach
    void setUp(){
        this.vehiculoRE = new VehiculoRE(
                2020,
                "Blanco",
                "Peugeot",
                "208",
                "AC 545 JG",
                null);
    }

    @Test
    void createVehiculo() throws InvalidFieldException {

        VehiculoDTO response = service.createVehiculo(vehiculoRE);

        assertThat(response).isNotNull();
        assertThat(response.getAnio()).isEqualTo(vehiculoRE.getAnio());
        assertThat(response.getPatente()).isEqualTo(vehiculoRE.getPatente());


    }

    @Test
    void updateVehiculo() throws EntityNotFoundException {

        VehiculoDTO response = service.updateVehiculo(1L,vehiculoRE);

        assertThat(response).isNotNull();
        assertThat(response.getAnio()).isEqualTo(vehiculoRE.getAnio());
        assertThat(response.getColor()).isEqualTo(vehiculoRE.getColor());
        assertThat(response.getMarca()).isEqualTo(vehiculoRE.getMarca());
        assertThat(response.getModelo()).isEqualTo(vehiculoRE.getModelo());
    }

    @Test
    void getVehiculoById() throws EntityNotFoundException {

        VehiculoDTO response = service.getVehiculoById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getAnio()).isEqualTo(2020);
        assertThat(response.getPatente()).isEqualTo("patente");
    }

    @Test
    void deleteVehiculo() throws EntityNotFoundException, InvalidFieldException {

        VehiculoDTO vehiculo = service.createVehiculo(vehiculoRE);
        Boolean response = service.deleteVehiculo(6L);

        assertThat(response).isTrue();
    }

    @Test
    void getAllVehiculo() {

        List<VehiculoDTO> responseList = service.getAllVehiculo();

        assertThat(responseList).isNotNull();
    }

    @Test
    void validarVehiculo() throws EntityNotFoundException {

        VehiculoDTO response = service.getVehiculoByPatente("patente");

        assertThat(response).isNotNull();
        assertThat(response.getAnio()).isEqualTo(2020);
        assertThat(response.getPatente()).isEqualTo("patente");
    }

}