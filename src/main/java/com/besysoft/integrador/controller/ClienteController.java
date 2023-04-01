package com.besysoft.integrador.controller;


import com.besysoft.integrador.dto.dto.ClienteDTO;
import com.besysoft.integrador.dto.re.ClienteRE;
import com.besysoft.integrador.exceptions.EntityNotFoundException;
import com.besysoft.integrador.exceptions.InvalidFieldException;
import com.besysoft.integrador.service.IClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @ApiOperation(value = "Registrar Cliente",
            notes = "Se registra un nuevo cliente."
    )
    @Parameter(
            name = "Cliente",
            description = "Cliente nuevo",
            required = true,
            schema = @Schema(type = "ClienteRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "El cliente fue registrado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Cliente existente/ Error en algun campo")
    })

    @PostMapping("")
    public ResponseEntity<?> registrarCliente(@Valid @RequestBody ClienteRE clienteRE) throws EntityNotFoundException, InvalidFieldException {

        ClienteDTO response = clienteService.createClient(clienteRE);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiOperation(value = "Actualizar Cliente",
            notes = "Se actualiza un cliente."
    )
    @Parameter(
            name = "Id Cliente",
            description = "id del cliente a modificar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @Parameter(
            name = "Cliente",
            description = "Cliente actualizado",
            required = true,
            schema = @Schema(type = "ClienteRE"))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El cliente fue actualizado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido /  Error en algun campo")
    })

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCliente(@PathVariable("id") Long clienteId,@Valid @RequestBody ClienteRE clienteRE) throws EntityNotFoundException {

        ClienteDTO response =  clienteService.updateClient(clienteId,clienteRE);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Eliminar Cliente",
            notes = "Se elimina un cliente."
    )
    @Parameter(
            name = "Id Cliente",
            description = "id del cliente a eliminar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "El cliente fue eliminado Correctamente"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCliente(@PathVariable("id") Long id) throws EntityNotFoundException {

        return ResponseEntity.ok().body(clienteService.deleteClient(id));
    }

    @ApiOperation(value = "Listar Clientes",
            notes = "Trae todos los clientes guardados."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista con los clientes.")
    })
    @GetMapping("")
    public ResponseEntity<?> listarClientes() throws EntityNotFoundException {

        List<ClienteDTO> responseList = clienteService.getAllClient();

        return ResponseEntity.ok().body(responseList);
    }

    @ApiOperation(value = "Buscar por id",
            notes = "Se busca un cliente por su id."
    )
    @Parameter(
            name = "Id Cliente",
            description = "id del cliente a buscar",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1",
            in = ParameterIn.PATH
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el cliente encontrado"),
            @ApiResponse(responseCode = "400", description = "Id invalido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable("id") Long id) throws EntityNotFoundException {

        ClienteDTO response = clienteService.getClienteById(id);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Buscar por Email",
            notes = "Se busca un cliente por su email."
    )
    @Parameter(
            name = "Email Cliente",
            description = "Email del cliente a buscar",
            required = true,
            schema = @Schema(type = "String"),
            example = "test@hotmail.com"

    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el cliente encontrado"),
            @ApiResponse(responseCode = "400", description = "email invalido")
    })
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarClientePorEmail(@RequestParam(value = "email") String email) throws EntityNotFoundException {

        ClienteDTO response = clienteService.buscarPorEmail(email);

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "Vincular vehiculo",
            notes = "Se vincula un vehiculo con un cliente."
    )

    @Parameter(
            name = "Id Cliente",
            description = "id del cliente ",
            required = true,
            schema = @Schema(type = "Long"),
            example = "1"

    )
    @Parameter(
            name = "Patente",
            description = "Patente del vehiculo",
            required = true,
            schema = @Schema(type = "String"),
            example = "HSR533"

    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna el cliente modificado"),
            @ApiResponse(responseCode = "400", description = "Id invalido / patente invalida")
    })
    @PatchMapping("/agregar/vehiculo")
    public ResponseEntity<?> vincularVehiculo(@RequestParam(value = "clienteId") Long clienteId, @RequestParam(value = "patente") String patente) throws EntityNotFoundException {

        ClienteDTO response = clienteService.vincularVehiculo(clienteId,patente);

        return ResponseEntity.ok().body(response);
    }
}
