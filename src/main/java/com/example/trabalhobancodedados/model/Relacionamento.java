package com.example.trabalhobancodedados.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node("Pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Relacionamento {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private String dataNascimento;
    
    @Relationship(type = "AMIGO_DE", direction = Relationship.Direction.OUTGOING)
    @Builder.Default
    private Set<Relacao> amigos = new HashSet<>();
}