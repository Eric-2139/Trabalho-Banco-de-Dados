package com.example.trabalhobancodedados.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.example.trabalhobancodedados.repository.neo4j",
repositoryImplementationPostfix = "Neo4j")
public class Neo4jConfig {
    
}
