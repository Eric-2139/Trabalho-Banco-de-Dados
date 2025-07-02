package com.example.trabalhobancodedados.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relacao {
    
    @Id
    @GeneratedValue
    private Long id;

    private String tipo;

    @TargetNode
    private Relacionamento pessoa;
}