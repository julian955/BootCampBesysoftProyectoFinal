package com.besysoft.integrador.controller;

import com.besysoft.integrador.dto.dto.EmpleadoDTO;
import com.besysoft.integrador.dto.re.EmpleadoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IEmpleadoService;
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
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadoService;

    @ApiOperation(value = "Registrar Empleado",
            notes = "Se registra un nuevo Empleado."
    )
    @Parameter(
            name = "Empleadoo",
            description = "Nuevo Empleado",
            required = true,
            schema = @Schema(type = "EmpleadoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "El Empleado fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> generarEmpleado(@Valid @RequestBody EmpleadoRE empleadoRE) throws EntityNotFoundException {

        EmpleadoDTO response = empleadoService.createEmpleado(empleadoRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar Empleadoo",
            notes = "Se actualiza un Empleadoo."
    )
    @Parameter(
            name = "Id Empleado",
            description = "id del Empleadoo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Empleado",
            description = "Empleado actualizado",
            required = true,
            schema = @Schema(type = "EmpleadoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Empleado fue actualizado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id,@Valid @RequestBody EmpleadoRE empleadoRE) throws EntityNotFoundException {

        EmpleadoDTO response = empleadoService.updateEmpleado(id,empleadoRE);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Empleado",
            notes = "Se elimina un Empleado."
    )
    @Parameter(
            name = "Id Empleado",
            description = "id del Empleado a eliminar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Empleado fue eliminado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(empleadoService.deleteEmpleado(id));
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca un Empleado por su id."
    )
    @Parameter(
            name = "Id Empleadoo",
            description = "id del Empleado a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el Empleado encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmpleado(@PathVariable Long id) throws EntityNotFoundException {

        EmpleadoDTO response = empleadoService.getEmpleadoById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Listar Empleado",
            notes = "Trae todos los Empleados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con los Empleados.")
    })
    @GetMapping("")
    public ResponseEntity<?> obtenerEmpleados() throws EntityNotFoundException {

        List<EmpleadoDTO> responseList = empleadoService.getAllEmpleado();

        return ResponseEntity.ok().body(responseList);
    }
}
