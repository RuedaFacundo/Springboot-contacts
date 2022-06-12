package com.folcademy.Api.Controllers;

import com.folcademy.Api.Model.Domain.AdressDTO;
import com.folcademy.Api.Services.AdressService;
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

@RestController
@RequestMapping("/direcciones")
@SecurityRequirement(name  = "apiDeContactos")
public class AdressController {

    private final AdressService adressService;

    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @Operation(summary = "Get direcciones por filtro y paginadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obtuvo las direcciones filtradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdressDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Request invalida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No encontro las direcciones",
                    content = @Content) })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Page<AdressDTO>> findAllSorted(
            @RequestParam(required = false, defaultValue = "0") Integer pagina,
            @RequestParam(required = false, defaultValue = "5") Integer tamaño,
            @RequestParam(required = false) String calle,
            @RequestParam(required = false) String numero) {
        return ResponseEntity.ok(
                adressService.findWithFiltersAndPaginated(pagina, tamaño, calle, numero)
        );
    }
}
