package com.besysoft.integrador.controller;



import com.besysoft.integrador.dto.dto.MecanicoDTO;
import com.besysoft.integrador.dto.re.MecanicoRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.service.IMecanicoService;
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
@RequestMapping("/mecanicos")
public class MecanicoController {

    @Autowired
    private IMecanicoService mecanicoService;

    @ApiOperation(value = "Registrar Mecanico",
            notes = "Se registra un Mecanico."
    )
    @Parameter(
            name = "Mecanico",
            description = "Nuevo Mecanico",
            required = true,
            schema = @Schema(type = "MecanicoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "El Mecanico fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en algun campo")
    })
    @PostMapping("")
    public ResponseEntity<?> generarMecanico(@Valid @RequestBody MecanicoRE mecanicoRE) throws EntityNotFoundException {

        MecanicoDTO response = mecanicoService.createMecanico(mecanicoRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar  Mecanico",
            notes = "Se actualiza un Mecanico."
    )
    @Parameter(
            name = "Id Mecanico",
            description = "id del Mecanico a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Mecanico",
            description = " Mecanico actualizada",
            required = true,
            schema = @Schema(type = "MecanicoRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Mecanico fue actualizada Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMecanico(@PathVariable Long id, @Valid @RequestBody MecanicoRE mecanicoRE) throws EntityNotFoundException {

        MecanicoDTO response = mecanicoService.updateMecanico(id,mecanicoRE);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Mecanico",
            notes = "Se elimina un Mecanico."
    )
    @Parameter(
            name = "Id Mecanico",
            description = "id del Mecanico",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El Mecanico fue eliminado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMecanico(@PathVariable Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(mecanicoService.deleteMecanico(id));
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca un Mecanico por su id."
    )
    @Parameter(
            name = "Id Mecanico",
            description = "id del Mecanico a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el Mecanico encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarMecanico(@PathVariable Long id) throws EntityNotFoundException {

        MecanicoDTO response = mecanicoService.getMecanicoById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Listar Mecanico",
            notes = "Trae todos los Mecanico guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con los Mecanicos.")
    })
    @GetMapping("")
    public ResponseEntity<?> obtenerMecanicos() throws EntityNotFoundException {

        List<MecanicoDTO> responseList = mecanicoService.getAllMecanico();

        return ResponseEntity.ok().body(responseList);
    }
}
