package com.folcademy.Api.Model.Domain;

import com.folcademy.Api.Model.Entities.AppUserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    Integer id;
    String username;
    String password;
    List<AppUserRole> appUserRoles;
}
