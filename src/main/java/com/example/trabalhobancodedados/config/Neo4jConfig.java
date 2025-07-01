package com.example.trabalhobancodedados.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.trabalhobancodedados.repository.neo4j")
public class Neo4jConfig {
    
}
