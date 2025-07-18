package com.example.trabalhobancodedados.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.trabalhobancodedados.model.Pessoa;
import com.example.trabalhobancodedados.repository.postgresql.PessoaRepository;
import com.example.trabalhobancodedados.repository.redis.CacheRepository;
import com.example.trabalhobancodedados.repository.neo4j.RelacionamentoRepository;
import com.example.trabalhobancodedados.service.CacheCrud;
import com.example.trabalhobancodedados.model.Relacionamento;

@Service
public class PessoaService {

    private final PessoaRepository repository;
    private final LoggingService loggingService;
    private final CacheCrud cacheCrud;
    private final RelacionamentoRepository relacionamentoRepository;

    public PessoaService(PessoaRepository repository, LoggingService loggingService, CacheCrud cacheCrud,
            RelacionamentoRepository relacionamentoRepository) {
        this.repository = repository;
        this.loggingService = loggingService;
        this.cacheCrud = cacheCrud;
        this.relacionamentoRepository = relacionamentoRepository;
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        Pessoa criada = repository.save(pessoa);
        Relacionamento no = Relacionamento.builder()
                .id(criada.getId())
                .nome(criada.getNome())
                .dataNascimento(criada.getDataNascimento().toString())
                .build();
        relacionamentoRepository.save(no);
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
        Relacionamento no = relacionamentoRepository.findById(id).orElse(
                Relacionamento.builder()
                        .id(id)
                        .build());
        no.setNome(atualizada.getNome());
        no.setDataNascimento(atualizada.getDataNascimento().toString());
        relacionamentoRepository.save(no);
        loggingService.info("Pessoa atualizada: " + id);
        return atualizada;
    }

    public void deletarPessoa(Long id) {
        repository.deleteById(id);
        relacionamentoRepository.deleteById(id);
        loggingService.info("Pessoa deletada: " + id);
    }
}