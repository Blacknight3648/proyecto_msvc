package com.smedinamsvc.resenia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful - MSCV - Resenia")
                        .description("Esta es la API dedicada al microservicio de reseñas. Aquí podrás gestionar y consultar las reseñas asociadas a productos y clientes.")
                        .version("1.0.0")
                );
    }
}
