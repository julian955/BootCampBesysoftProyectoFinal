package com.besysoft.integrador.controller;



import com.besysoft.integrador.dto.dto.RepuestoDTO;
import com.besysoft.integrador.dto.re.RepuestoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IRepuestoService;
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
@RequestMapping("/repuestos")
public class RepuestoController {

    @Autowired
    private IRepuestoService repuestoService;

    @ApiOperation(value = "Registrar Repuesto",
            notes = "Se registra un Repuesto."
    )
    @Parameter(
            name = "Repuesto",
            description = "Nuevo Repuesto",
            required = true,
            schema = @Schema(type = "RepuestoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "El Repuesto fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> generarRepuesto(@Valid @RequestBody RepuestoRE repuestoRE) throws EntityNotFoundException {

        RepuestoDTO response = repuestoService.createRepuesto(repuestoRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar  Repuesto",
            notes = "Se actualiza un Repuesto."
    )
    @Parameter(
            name = "Id Repuesto",
            description = "id del Repuesto a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Repuesto",
            description = " Repuesto actualizado",
            required = true,
            schema = @Schema(type = "RepuestoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Repuesto fue actualizada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarRepuesto(@PathVariable Long id,@Valid @RequestBody RepuestoRE repuestoRE) throws EntityNotFoundException {

        RepuestoDTO response = repuestoService.updateRepuesto(id,repuestoRE);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Repuesto",
            notes = "Se elimina un Repuesto."
    )
    @Parameter(
            name = "Id Repuesto",
            description = "id del Repuesto",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Repuesto fue eliminado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRepuesto(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(repuestoService.deleteRepuesto(id));
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca un Repuesto por su id."
    )
    @Parameter(
            name = "Id Repuesto",
            description = "id del Repuesto a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el Repuesto encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRepuesto(@PathVariable Long id) throws EntityNotFoundException {

        RepuestoDTO response = repuestoService.getRepuestoById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Listar Repuestos",
            notes = "Trae todos los Repuestos guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con los Repuestos.")
    })
    @GetMapping("")
    public ResponseEntity<?> obtenerRepuestos() throws EntityNotFoundException {

        List<RepuestoDTO> responseList = repuestoService.getAllRepuesto();

        return ResponseEntity.ok().body(responseList);
    }
}
