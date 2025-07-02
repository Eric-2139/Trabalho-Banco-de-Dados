package com.example.trabalhobancodedados.repository.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.trabalhobancodedados.model.Relacionamento;

public interface RelacionamentoRepository extends Neo4jRepository<Relacionamento, Long> {
    
}
