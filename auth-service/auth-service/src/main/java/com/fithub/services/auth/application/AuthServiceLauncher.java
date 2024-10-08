package com.fithub.services.auth.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.fithub.services.auth" })
@EnableJpaRepositories("com.fithub.services.auth.dao.repository")
@EntityScan(basePackages = { "com.fithub.services.auth.dao.model" })
public class AuthServiceLauncher {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceLauncher.class, args);
    }

}