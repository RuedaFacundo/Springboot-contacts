package com.folcademy.Api.Model.Domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdressDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Integer idDireccion;
    String calle;
    String numero;
}
