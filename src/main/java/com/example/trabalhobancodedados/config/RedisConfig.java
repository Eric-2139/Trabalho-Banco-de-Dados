package com.example.trabalhobancodedados.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.example.trabalhobancodedados.repository.redis")
public class RedisConfig {
    
}
