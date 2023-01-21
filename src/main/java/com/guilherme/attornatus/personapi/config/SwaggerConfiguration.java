package com.guilherme.attornatus.personapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    private static final String API_TITLE = "Person API Attornatus";
    private static final String API_DESCRIPTION = "REST API for Person Registration";
    private static final String API_VERSION = "1.0.0";
    private static final String CONTACT_NAME = "Guilherme Gabriel da Silva";
    private static final String CONTACT_GITHUB = "https://github.com/GuilhermeGorges";
    private static final String CONTACT_EMAIL = "guilhermegabriel1992@gmail.com";

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(API_TITLE)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION)
                        .contact(new Contact()
                                .name(CONTACT_NAME)
                                .url(CONTACT_GITHUB)
                                .email(CONTACT_EMAIL)));
    }
}
