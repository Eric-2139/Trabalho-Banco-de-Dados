package com.example.trabalhobancodedados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.trabalhobancodedados.model.Pessoa;
import com.example.trabalhobancodedados.repository.postgresql.PessoaRepository;

@Service
public class PessoaService {
    

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return repository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa dados) {
        Pessoa existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa nao encontrada"));

        existente.setNome(dados.getNome());
        existente.setEmail(dados.getEmail());
        existente.setCpf(dados.getCpf());
        existente.setDataNascimento(dados.getDataNascimento());

        return repository.save(existente);
    }

    public void deletarPessoa(Long id) {
        repository.deleteById(id);
    }
}