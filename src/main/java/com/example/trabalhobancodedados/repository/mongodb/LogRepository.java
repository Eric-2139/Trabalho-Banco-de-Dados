package com.example.trabalhobancodedados.repository.mongodb;

import com.example.trabalhobancodedados.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
    
}