package com.besysoft.integrador.controller;

import com.besysoft.integrador.dto.dto.DetalleOrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.DetalleOrdenTrabajoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.imp.DetalleOrdenTrabajoService;
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
@RequestMapping("/detalle/trabajo")
public class DetalleOrdenTrabajoController {

    @Autowired
    private DetalleOrdenTrabajoService detalleOrdenTrabajoService;

    @ApiOperation(value = "Registrar Detalle Orden Trabajo",
            notes = "Se registra un nuevo Detalle Orden Trabajo."
    )
    @Parameter(
            name = "Detalle Orden Trabajo",
            description = "Nuevo Detalle Orden Trabajo",
            required = true,
            schema = @Schema(type = "DetalleOrdenTrabajoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "El Detalle Orden Trabajo fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> crearDetalleOT(@Valid @RequestBody DetalleOrdenTrabajoRE detalleOrdenTrabajo) throws EntityNotFoundException {

        DetalleOrdenTrabajoDTO response = detalleOrdenTrabajoService.createDetalle(detalleOrdenTrabajo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar Detalle Orden Trabajo",
            notes = "Se actualiza un Detalle Orden Trabajo."
    )
    @Parameter(
            name = "Id Detalle Orden Trabajo",
            description = "id del Detalle Orden Trabajo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Detalle Orden Trabajo",
            description = "Detalle Orden Trabajo actualizado",
            required = true,
            schema = @Schema(type = "DetalleOrdenTrabajoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Detalle Orden Trabajo fue actualizado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDetalle(@PathVariable Long id, @Valid @RequestBody DetalleOrdenTrabajoRE detalleOrdenTrabajo) throws EntityNotFoundException {

        DetalleOrdenTrabajoDTO response = detalleOrdenTrabajoService.updateDetalle(id,detalleOrdenTrabajo);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Detalle Orden Trabajo",
            notes = "Se elimina un Detalle Orden Trabajo."
    )
    @Parameter(
            name = "Id Detalle Orden Trabajo",
            description = "id del Detalle Orden Trabajo a eliminar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Detalle Orden Trabajo fue eliminado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDetalle(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(detalleOrdenTrabajoService.deleteDetalle(id));
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca un Detalle Orden Trabajo por su id."
    )
    @Parameter(
            name = "Id Detalle Orden Trabajo",
            description = "id del Detalle Orden Trabajo a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el Detalle Orden Trabajo encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarDetalle(@PathVariable Long id) throws EntityNotFoundException {

        DetalleOrdenTrabajoDTO response = detalleOrdenTrabajoService.getDetalleById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Listar Detalle Orden Trabajo",
            notes = "Trae todos los Detalles de Orden Trabajo guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con los Detalles de Ordenes de Trabajo.")
    })
    @GetMapping("")
    public ResponseEntity<?> obtenerDetallesOT() throws EntityNotFoundException {

        List<DetalleOrdenTrabajoDTO> responseList = detalleOrdenTrabajoService.getAllDetalle();

        return ResponseEntity.ok().body(responseList);
    }

}
