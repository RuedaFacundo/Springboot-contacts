package com.folcademy.Api.Model.Repositories;

import com.folcademy.Api.Model.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  boolean existsByUsername(String username);

  AppUser findByUsername(String username);

}
