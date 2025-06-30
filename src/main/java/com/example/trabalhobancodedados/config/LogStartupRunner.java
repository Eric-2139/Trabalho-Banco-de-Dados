package com.example.trabalhobancodedados.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.trabalhobancodedados.service.LoggingService;

@Component
public class LogStartupRunner implements CommandLineRunner {
    private final LoggingService loggingService;

    public LogStartupRunner(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public void run(String... args) throws Exception {
        loggingService.info("Application started");
    }
}