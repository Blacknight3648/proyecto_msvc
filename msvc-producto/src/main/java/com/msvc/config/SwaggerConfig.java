package com.msvc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api RestFull - MSVC - Productos")
                        .description("Agrega y quita productos.")
                        .version("1.0.0")

                );
    }
}
