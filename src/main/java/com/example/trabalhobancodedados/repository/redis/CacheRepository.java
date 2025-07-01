package com.example.trabalhobancodedados.repository.redis;

import org.springframework.data.repository.CrudRepository;

import com.example.trabalhobancodedados.model.Cache;

public interface CacheRepository extends CrudRepository<Cache, String> {
    
}
