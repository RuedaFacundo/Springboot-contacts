package com.folcademy.Api.Controllers;

import com.folcademy.Api.Model.Domain.UserDTO;
import com.folcademy.Api.Model.Entities.AppUser;
import com.folcademy.Api.Services.UserService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(tags = "auth")
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @Operation(summary = "Registrar un usuario")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Registro el usuario", response = String.class),
          @ApiResponse(code = 400, message = "Something went wrong"),
          @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  @PostMapping
  public String registrarUsuario(@ApiParam("Signup User") @RequestBody UserDTO user) {
    return userService.signup(modelMapper.map(user, AppUser.class));
  }

  @Operation(summary = "Obtener token de usuario")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Obtuvo el token", response = String.class),
          @ApiResponse(code = 400, message = "Something went wrong"),
          @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  @PostMapping("/token")
  public String login(
                      @ApiParam("Username") @RequestParam String username,
                      @ApiParam("Password") @RequestParam String password) {
    return userService.signin(username, password);
  }

  @Operation(summary = "Obtener un usuario registrado")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Obtuvo el usuario", response = UserDTO.class),
          @ApiResponse(code = 400, message = "Something went wrong"),
          @ApiResponse(code = 403, message = "Access denied"),
          @ApiResponse(code = 404, message = "The user doesn't exist"),
          @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  @GetMapping
  public UserDTO search(@RequestParam(required = false, defaultValue = "") String username) {
    return modelMapper.map(userService.search(username), UserDTO.class);
  }

}
