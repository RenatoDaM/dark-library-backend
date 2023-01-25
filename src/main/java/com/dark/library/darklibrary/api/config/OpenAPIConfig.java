package com.dark.library.darklibrary.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenAPIConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("dark-library")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI darkLibraryOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Library API")
                .description("You can create and manage books with this REST API.")
                .version("v1"));
    }



}
