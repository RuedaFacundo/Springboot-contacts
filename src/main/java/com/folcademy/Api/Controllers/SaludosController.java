package com.folcademy.Api.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludos")
public class SaludosController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hola! Mi nombre es Facundo y es mi primer API");
    }

    @PostMapping("/goodbye")
    public ResponseEntity<String> goodBye(){
        return ResponseEntity.ok("Has hecho una peticion POST. Hasta luego!");
    }
}
