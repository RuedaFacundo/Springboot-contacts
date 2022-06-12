package com.folcademy.Api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de contactos", version = "1.0",
		description = "Api de contactos. Para realizar peticiones de contactos y direcciones debe autenticarse con el rol de administrador."))
@SecurityScheme(name = "apiDeContactos", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, bearerFormat = "JWT", description = "Ingrese el JWT para autenticarse")
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
