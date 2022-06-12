package com.folcademy.Api.Model.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name ="users")
@Data
@NoArgsConstructor
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(unique = true, nullable = false)
  private String username;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  private String password;

  @ElementCollection(fetch = FetchType.EAGER)
  List<AppUserRole> appUserRoles;

}
