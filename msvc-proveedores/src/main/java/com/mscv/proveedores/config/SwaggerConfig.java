package com.mscv.proveedores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTFull - MSCV Proveedores")
                        .description("Esta es la API dedicada al microservicio de proveedores. Aquí podrás gestionar y consultar las operaciones asociadas a los Proveedores como microservicio")
                        .version("1.0.0")
                );
    }
}
