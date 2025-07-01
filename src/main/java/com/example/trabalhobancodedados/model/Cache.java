package com.example.trabalhobancodedados.model;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.*;

@RedisHash("PessoaModel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cache {
    
    @Id
    private String id;  

    private String name;
    private String email;
    private String cpf;
    private String dataNascimento;
}
