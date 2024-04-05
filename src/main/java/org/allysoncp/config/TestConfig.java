package org.allysoncp.config;

import org.allysoncp.repositories.UsuarioRepository;
import org.allysoncp.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public DBService instanciaBaseDeDados() {
        dbService.instanciaBaseDeDados();
        return dbService;
    }
}

