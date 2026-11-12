package com.bastidas.pqr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LexIA - PQR & Legal Assistant Microservice")
                        .version("1.0.0")
                        .description("REST API for managing PQR tickets and legal document assistance within the LexIA platform.")
                        .contact(new Contact()
                                .name("LexIA Development Team")
                                .email("dev@lexia.legal")));
    }
}
