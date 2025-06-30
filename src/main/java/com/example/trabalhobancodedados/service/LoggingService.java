package com.example.trabalhobancodedados.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.trabalhobancodedados.model.Log;
import com.example.trabalhobancodedados.repository.mongodb.LogRepository;

@Service
public class LoggingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingService.class);
    private final LogRepository logRepository;

    public LoggingService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void info(String message) {
        LOGGER.info(message);
        logRepository.save(new Log(null, "INFO", message, LocalDateTime.now()));
    }
}