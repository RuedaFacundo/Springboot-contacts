package com.folcademy.Api.Controllers;

import com.folcademy.Api.Model.Domain.AdressDTO;
import com.folcademy.Api.Model.Domain.PersonDTO;
import com.folcademy.Api.Services.PersonService;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/contacto")
@SecurityRequirement(name  = "apiDeContactos")
public class ContactController {

    private final PersonService personService;

    public ContactController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Get All contactos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtuvo los contactos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontro los contactos",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(
                personService.findAll()
        );
    }

    @Operation(summary = "Get All contactos paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtuvo los contactos paginados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontro los contactos",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<PersonDTO>> findAllPaginated(
            @RequestParam(required = false, defaultValue = "0") Integer number,
            @RequestParam(required = false, defaultValue = "5") Integer size) {
        return ResponseEntity.ok(
                personService.findAllPaginated(number, size)
        );
    }

    @Operation(summary = "Get All contactos por filtro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtuvo los contactos filtrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontro los contactos",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/filtered")
    public ResponseEntity<List<PersonDTO>> findAllSorted(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String celular) {
        return ResponseEntity.ok(
                personService.findWithFilters(nombre, celular)
        );
    }

    @Operation(summary = "Get contacto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtuvo el contacto",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontro el contacto",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(
                personService.getById(id)
        );
    }

    @Operation(summary = "Get direcciones del contacto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtuvo las direcciones del contacto",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdressDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontro las direcciones del contacto",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}/direcciones")
    public ResponseEntity<List<AdressDTO>> getAdressByIdContact(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(
                personService.getAdressByIdContact(id)
        );
    }

    @Operation(summary = "Agregar contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contacto guardado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No pudo guardar el contacto",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Map<String, String> save(@RequestBody @NotNull PersonDTO dto){
        PersonDTO personDTO = personService.savePerson(dto);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", personDTO.getId().toString());
        map.put("mensaje: ", "Añadido correctamente");
        return map;
    }

    @Operation(summary = "Eliminar contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contacto eliminado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No pudo eliminar el contacto",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(
                personService.delete(id)
        );
    }

    @Operation(summary = "Actualizar contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contacto actualizado exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No pudo actualizar el contacto",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping()
    public ResponseEntity<String> put(@RequestBody @NotNull PersonDTO dto) {
        return ResponseEntity.ok(
                Objects.nonNull(personService.updatePerson(dto)) ?
                        "Registro actualizado correctamente" : "No se pudo actualizar el registro"
        );
    }
}
