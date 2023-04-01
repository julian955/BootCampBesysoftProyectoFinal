package com.besysoft.integrador.controller;



import com.besysoft.integrador.dto.dto.OrdenTrabajoDTO;
import com.besysoft.integrador.dto.re.OrdenTrabajoRE;
import com.besysoft.integrador.dto.re.PagoRE;
import com.besysoft.integrador.exceptions.BadStatusException;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.imp.OrdenTrabajoService;
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
@RequestMapping("/orden/trabajo")
public class OrdenTrabajoController {

    @Autowired
    private OrdenTrabajoService ordenTrabajoService;


    @ApiOperation(value = "Registrar Orden de Trabajo",
            notes = "Se registra una nueva Orden de Trabajo."
    )
    @Parameter(
            name = "Orden de Trabajo",
            description = "Nueva Orden de Trabajo",
            required = true,
            schema = @Schema(type = "OrdenTrabajoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "La Orden de Trabajo fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> generarOrdenTrabajo(@Valid @RequestBody OrdenTrabajoRE ordenTrabajoRE) throws EntityNotFoundException {

        OrdenTrabajoDTO response = ordenTrabajoService.createOrdenTrabajo(ordenTrabajoRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @ApiOperation(value = "Buscar por id",
            notes = "Se busca una Orden de Trabajo por su id."
    )
    @Parameter(
            name = "Id Orden de Trabajo",
            description = "id de la Orden de Trabajo a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna la Orden de Trabajo encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerOrdenTrabajo(@PathVariable Long id) throws EntityNotFoundException, BadStatusException {

        OrdenTrabajoDTO response = ordenTrabajoService.getOrdenTrabajoById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Orden de Trabajo",
            notes = "Se elimina una Orden de Trabajo."
    )
    @Parameter(
            name = "Id Orden de Trabajo",
            description = "id de la Orden de Trabajo a eliminar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Orden de Trabajo fue eliminada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrdenTrabajo(@PathVariable Long id) throws EntityNotFoundException, BadStatusException {

        return ResponseEntity.ok().body(ordenTrabajoService.deleteOrdenTrabajo(id));
    }

    @ApiOperation(value = "Listar Orden de Trabajo",
            notes = "Trae todas las Ordenes de Trabajo guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con las Ordenes de Trabajo.")
    })
    @GetMapping("")
    public ResponseEntity<?> listarOrdenesTrabajo() throws EntityNotFoundException, BadStatusException {

        List<OrdenTrabajoDTO> listResponse = ordenTrabajoService.getAllOrdenTrabajo();
        return ResponseEntity.ok().body(listResponse);
    }


    @ApiOperation(value = "Iniciar Trabajo",
            notes = "Se cambia el estado de una Orden de Trabajo."
    )
    @Parameter(
            name = "Id Orden de Trabajo",
            description = "id de la Orden de Trabajo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Orden de Trabajo fue modificada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @PatchMapping("/iniciar/{id}")
    public ResponseEntity<?> iniciarReparacion(@PathVariable("id") Long id) throws EntityNotFoundException, BadStatusException {

        OrdenTrabajoDTO response = ordenTrabajoService.iniciarReparacion(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Finalizar Trabajo",
            notes = "Se cambia el estado de una Orden de Trabajo."
    )
    @Parameter(
            name = "Id Orden de Trabajo",
            description = "id de la Orden de Trabajo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Orden de Trabajo fue modificada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @PatchMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarReparacion(@PathVariable("id") Long id) throws EntityNotFoundException, BadStatusException {

        OrdenTrabajoDTO response = ordenTrabajoService.finalizarReparacion(id);

        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "facturar Trabajo",
            notes = "Se cambia el estado de una Orden de Trabajo."
    )
    @Parameter(
            name = "Id Orden de Trabajo",
            description = "id de la Orden de Trabajo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1"
    )
    @Parameter(
            name = "Id administrativo",
            description = "id del empleado administrativo",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1"
    )
    @Parameter(
            name = "Pago",
            description = "Forma de pago del trabajo",
            required = true,
            schema = @Schema(type = "PagoRE")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Orden de Trabajo fue modificada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @PatchMapping("/facturar")
    public ResponseEntity<?> generarFacturacionYPago(@RequestParam(value = "ordenTrabajoId") Long ordenTrabajoId,@RequestParam(value = "administrativoId") Long administrativoId,@Valid @RequestBody PagoRE pago) throws EntityNotFoundException, BadStatusException {

        OrdenTrabajoDTO response = ordenTrabajoService.generarFacturacionyPago(ordenTrabajoId,administrativoId,pago);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Cerrar Trabajo",
            notes = "Se cambia el estado de una Orden de Trabajo."
    )
    @Parameter(
            name = "Id Orden de Trabajo",
            description = "id de la Orden de Trabajo a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Orden de Trabajo fue modificada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @PatchMapping("/entrega/{id}")
    public ResponseEntity<?> entregarVehiculo(@PathVariable("id") Long ordenTrabajoId) throws EntityNotFoundException, BadStatusException {

        OrdenTrabajoDTO response = ordenTrabajoService.entregaVehiculo(ordenTrabajoId);

        return ResponseEntity.ok().body(response);
    }
}
