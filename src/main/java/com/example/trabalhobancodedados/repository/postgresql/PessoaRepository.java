package com.example.trabalhobancodedados.repository.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.trabalhobancodedados.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    Pessoa findByCpf(String cpf);
}
