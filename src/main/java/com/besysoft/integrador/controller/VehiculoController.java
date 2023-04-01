package com.besysoft.integrador.controller;




import com.besysoft.integrador.dto.dto.VehiculoDTO;
import com.besysoft.integrador.dto.re.VehiculoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;
import com.besysoft.integrador.service.IVehiculoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private IVehiculoService vehiculoService;

    @ApiOperation(value = "Registrar Vehiculo",
            notes = "Se registra un Vehiculo."
    )
    @Parameter(
            name = "Vehiculo",
            description = "Nuevo Vehiculo",
            required = true,
            schema = @Schema(type = "VehiculoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "El Vehiculo fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> registrarVehiculo(@Valid @RequestBody VehiculoRE vehiculoRE) throws InvalidFieldException {

        VehiculoDTO response = vehiculoService.createVehiculo(vehiculoRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar  Vehiculo",
            notes = "Se actualiza un Vehiculo."
    )
    @Parameter(
            name = "Id Vehiculo",
            description = "id del Vehiculo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Vehiculo",
            description = " Vehiculo actualizado",
            required = true,
            schema = @Schema(type = "VehiculoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Vehiculo fue actualizada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVehiculo(@PathVariable Long id,@Valid @RequestBody VehiculoRE vehiculoRE) throws EntityNotFoundException {

        VehiculoDTO response =  vehiculoService.updateVehiculo(id,vehiculoRE);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Vehiculo",
            notes = "Se elimina un Vehiculo."
    )
    @Parameter(
            name = "Id Vehiculo",
            description = "id del Vehiculo",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Vehiculo fue eliminado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(vehiculoService.deleteVehiculo(id));
    }

    @ApiOperation(value = "Listar Vehiculos",
            notes = "Trae todos los Vehiculos guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con los Vehiculos.")
    })
    @GetMapping("")
    public ResponseEntity<?> listarVehiculos() throws EntityNotFoundException {

        List<VehiculoDTO> responseList = vehiculoService.getAllVehiculo();

        return ResponseEntity.ok().body(responseList);
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca un Vehiculo por su id."
    )
    @Parameter(
            name = "Id Vehiculo",
            description = "id del Vehiculo a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el Vehiculo encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVehiculoPorId(@PathVariable Long id) throws EntityNotFoundException {

        VehiculoDTO response =  vehiculoService.getVehiculoById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Buscar por patente",
            notes = "Se busca un Vehiculo por su patente."
    )
    @Parameter(
            name = "patente Vehiculo",
            description = "patente del Vehiculo a buscar",
            required = true,
            schema = @Schema(type = "String"),
            example = "1"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el Vehiculo encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarVehiculoPorPatente(@RequestParam(value = "patente") String patente) throws EntityNotFoundException {

        return ResponseEntity.ok().body(vehiculoService.getVehiculoByPatente(patente));
    }
}
