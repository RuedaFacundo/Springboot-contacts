package com.folcademy.Api.Model.Domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Integer id;
    String nombre;
    String celular;
    List<AdressDTO> direcciones;
}
