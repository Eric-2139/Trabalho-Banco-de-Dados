package com.example.trabalhobancodedados.service;

import java.util.concurrent.TimeUnit;

import com.example.trabalhobancodedados.repository.postgresql.PessoaRepository;
import org.springframework.stereotype.Service;
import com.example.trabalhobancodedados.model.Pessoa;
import lombok.*;

@Service
@RequiredArgsConstructor

public class CacheCrud {
    private final CacheService cacheService;
    private final PessoaRepository pessoaRepository; // Injetado para operações de cache

    // ---- Métodos limpos e focados na regra de negócio ----
    public Pessoa buscarPorCpf(String cpf) {
        // 1. Tenta buscar do Redis (via cacheService)
        Pessoa pessoaModel = cacheService.buscarPessoaPorCpf(cpf);
        if (pessoaModel != null) {
            return pessoaModel;
        }

        // 2. Busca no banco de dados se não achar no Redis
       pessoaModel = pessoaRepository.findByCpf(cpf);
        if (pessoaModel != null) {
            // 3. Salva no Redis (via cacheService)
            cacheService.salvarPessoa(pessoaModel, 1, TimeUnit.HOURS);
        }
        return pessoaModel;
    }

    public Pessoa salvarPessoaModel(Pessoa pessoaModel) {
        Pessoa PessoaModelSalva = pessoaRepository.save(pessoaModel);
        cacheService.salvarPessoa(PessoaModelSalva, 1, TimeUnit.HOURS); // Atualiza cache
        return PessoaModelSalva;
    }

    public void deletarPorCpf(String cpf) {
        cacheService.deletarPessoa(cpf); // Remove do cache
    }
}
