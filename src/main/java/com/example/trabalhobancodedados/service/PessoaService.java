package com.example.trabalhobancodedados.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.trabalhobancodedados.model.Pessoa;
import com.example.trabalhobancodedados.repository.postgresql.PessoaRepository;
import com.example.trabalhobancodedados.repository.redis.CacheRepository;
import com.example.trabalhobancodedados.service.CacheCrud;

@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final LoggingService loggingService;
    private final CacheCrud cacheCrud;

     public PessoaService(PessoaRepository repository, LoggingService loggingService, CacheCrud cacheCrud) {
        this.repository = repository;
        this.loggingService = loggingService;
        this.cacheCrud = cacheCrud;
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        Pessoa criada = repository.save(pessoa);
        loggingService.info("Pessoa criada: " + criada.getId());
        return criada;
    }

    public List<Pessoa> listarPessoas() {
        List<Pessoa> pessoas = repository.findAll();
        loggingService.info("Listagem de pessoas consultada");
        return pessoas;
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        loggingService.info("Busca de pessoa por id: " + id);
        return pessoa;
    }

    public Optional<Pessoa> buscarPorCpf(String cpf) {
        loggingService.info("Busca de pessoa por cpf: " + cpf);
        Pessoa pessoa = cacheCrud.buscarPorCpf(cpf);
        return Optional.ofNullable(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa dados) {
        Pessoa existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa nao encontrada"));

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setCpf(dados.getCpf());
        existente.setDataNascimento(dados.getDataNascimento());

        Pessoa atualizada = repository.save(existente);
        loggingService.info("Pessoa atualizada: " + id);
        return atualizada;
    }

    public void deletarPessoa(Long id) {
        repository.deleteById(id);
        loggingService.info("Pessoa deletada: " + id);
    }
}