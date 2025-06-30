package com.example.trabalhobancodedados.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.trabalhobancodedados.repository.mongodb")
public class MongoConfig {
    
}