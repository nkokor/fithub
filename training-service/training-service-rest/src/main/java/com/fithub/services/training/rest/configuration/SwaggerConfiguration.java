package com.fithub.services.training.rest.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    public SwaggerConfiguration() {
        super();
    }

    @Bean
    public GroupedOpenApi appointmentApi() {
        return GroupedOpenApi.builder().group("appointment-api").packagesToScan("com.fithub.services.training.rest.appointment").build();
    }

    @Bean
    public GroupedOpenApi songApi() {
        return GroupedOpenApi.builder().group("song-api").packagesToScan("com.fithub.services.training.rest.song").build();
    }

    @Bean
    public GroupedOpenApi clientApi() {
        return GroupedOpenApi.builder().group("client-api").packagesToScan("com.fithub.services.training.rest.client").build();
    }

    @Bean
    public GroupedOpenApi imageApi() {
        return GroupedOpenApi.builder().group("image-api").packagesToScan("com.fithub.services.training.rest.image").build();
    }

    @Bean
    public GroupedOpenApi progressionStatsApi() {
        return GroupedOpenApi.builder().group("progression-stats-api").packagesToScan("com.fithub.services.training.rest.progressionstats")
                .build();
    }

}