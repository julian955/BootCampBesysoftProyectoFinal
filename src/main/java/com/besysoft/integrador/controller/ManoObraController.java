package com.besysoft.integrador.controller;

import com.besysoft.integrador.dto.dto.ManoObraDTO;
import com.besysoft.integrador.dto.re.ManoObraRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IManoObraService;
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
@RequestMapping("/mano/obra")
public class ManoObraController {

    @Autowired
    private IManoObraService manoObraService;

    @ApiOperation(value = "Registrar Mano de Obra",
            notes = "Se registra una nueva Mano de Obra."
    )
    @Parameter(
            name = "Detalle Mano de Obra",
            description = "Nuevo Mano de Obra",
            required = true,
            schema = @Schema(type = "ManoObraRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "La Mano de Obra fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> generarManoObra(@Valid @RequestBody ManoObraRE manoObraRE) throws EntityNotFoundException {

        ManoObraDTO response = manoObraService.createManoObra(manoObraRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar  Mano de Obra",
            notes = "Se actualiza una  Mano de Obra."
    )
    @Parameter(
            name = "Id Mano de Obra",
            description = "id de la  Mano de Obra a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Mano de Obra",
            description = " Mano de Obra actualizada",
            required = true,
            schema = @Schema(type = "ManoObraRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Mano de Obra fue actualizada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarManoObra(@PathVariable("id") Long manoObraId,@Valid @RequestBody ManoObraRE manoObraRE) throws EntityNotFoundException {

        ManoObraDTO response = manoObraService.updateManoObra(manoObraId,manoObraRE);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Mano de Obra",
            notes = "Se elimina una Mano de Obra."
    )
    @Parameter(
            name = "Id Mano de Obra",
            description = "id de la Mano de Obra a eliminar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "La Mano de Obra fue eliminada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarManoObra(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(manoObraService.deleteManoObra(id));
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca una Mano de Obra por su id."
    )
    @Parameter(
            name = "Id Mano de Obra",
            description = "id de la Mano de Obra a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna la Mano de Obra encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarManoObra(@PathVariable Long id) throws EntityNotFoundException {

        ManoObraDTO response = manoObraService.getManoObraById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Listar Mano de Obra",
            notes = "Trae todas las Mano de Obra guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con las Mano de Obra.")
    })
    @GetMapping("")
    public ResponseEntity<?> listarManoObra() throws EntityNotFoundException {

        List<ManoObraDTO> response = manoObraService.getAllManoObra();

        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "Asignar mecanico",
            notes = "Se asigna un mecanico a una Mano de Obra."
    )
    @Parameter(
            name = "Id Mano de Obra",
            description = "id de la Mano de Obra a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1"
    )
    @Parameter(
            name = "Id Mecanico",
            description = "id del Mecanico",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1"

    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna la Mano de Obra modificada"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @PatchMapping("/asignar/mecanico")
    public ResponseEntity<?> asignarMecanico(@RequestParam(value = "manoObraId") Long manoObraId, @RequestParam(value = "mecanicoId") Long mecanicoId) throws EntityNotFoundException {

        return ResponseEntity.ok().body(manoObraService.asignarMecanico(manoObraId,mecanicoId));
    }
}
