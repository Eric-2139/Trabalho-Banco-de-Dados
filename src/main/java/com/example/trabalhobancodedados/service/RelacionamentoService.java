package com.example.trabalhobancodedados.service;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.example.trabalhobancodedados.model.Pessoa;
import com.example.trabalhobancodedados.model.Relacao;
import com.example.trabalhobancodedados.model.Relacionamento;
import com.example.trabalhobancodedados.repository.neo4j.RelacionamentoRepository;
import com.example.trabalhobancodedados.repository.postgresql.PessoaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelacionamentoService {

    private final RelacionamentoRepository relacionamentoRepository;
    private final PessoaRepository pessoaRepository;

    public void criarRelacao(Long idOrigem, Long idDestino, String tipo) {
        Pessoa origem = pessoaRepository.findById(idOrigem)
                .orElseThrow(() -> new RuntimeException("Pessoa nao encontrada: " + idOrigem));
        Pessoa destino = pessoaRepository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Pessoa nao encontrada: " + idDestino));

        Relacionamento noOrigem = relacionamentoRepository.findById(idOrigem).orElse(
                Relacionamento.builder()
                        .id(idOrigem)
                        .nome(origem.getNome())
                        .dataNascimento(origem.getDataNascimento().toString())
                        .relacoes(new HashSet<>())
                        .build());

        Relacionamento noDestino = relacionamentoRepository.findById(idDestino).orElse(
                Relacionamento.builder()
                        .id(idDestino)
                        .nome(destino.getNome())
                        .dataNascimento(destino.getDataNascimento().toString())
                        .relacoes(new HashSet<>())
                        .build());

        Relacao relacao = Relacao.builder()
                .tipo(tipo)
                .pessoa(noDestino)
                .build();

        noOrigem.getRelacoes().add(relacao);

        relacionamentoRepository.save(noDestino); // garante que destino existe
        relacionamentoRepository.save(noOrigem);
    }
}