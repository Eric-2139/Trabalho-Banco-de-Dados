package com.example.trabalhobancodedados.cli;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.trabalhobancodedados.model.Pessoa;
import com.example.trabalhobancodedados.service.PessoaService;
import com.example.trabalhobancodedados.service.LoggingService;
import com.example.trabalhobancodedados.service.RelacionamentoService;

@Component
public class PessoaCommandLineRunner implements CommandLineRunner {

    private final PessoaService service;
    private final LoggingService loggingService;
    private final RelacionamentoService relacionamentoService;

    public PessoaCommandLineRunner(PessoaService service, LoggingService loggingService,
            RelacionamentoService relacionamentoService) {
        this.service = service;
        this.loggingService = loggingService;
        this.relacionamentoService = relacionamentoService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1 - Criar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Atualizar pessoa");
            System.out.println("4 - Deletar pessoa");
            System.out.println("5 - Buscar pessoa por ID");
            System.out.println("6 - Buscar pessoa por CPF (cache)");
            System.out.println("7 - Mostrar logs");
            System.out.println("8 - Listar pessoas no Neo4j");
            System.out.println("9 - Criar relacionamento");
            System.out.println("10 - Mostrar relacionamentos");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    Pessoa nova = new Pessoa();
                    System.out.print("Nome: ");
                    nova.setNome(scanner.nextLine());
                    System.out.print("Email: ");
                    nova.setEmail(scanner.nextLine());
                    System.out.print("CPF: ");
                    nova.setCpf(scanner.nextLine());
                    System.out.print("Data Nascimento (yyyy-mm-dd): ");
                    nova.setDataNascimento(LocalDate.parse(scanner.nextLine()));
                    Pessoa criada = service.criarPessoa(nova);
                    System.out.println("Criada com id: " + criada.getId());
                    break;
                case "2":
                    service.listarPessoas().forEach(System.out::println);
                    break;
                case "3":
                    System.out.print("ID da pessoa: ");
                    Long id = Long.parseLong(scanner.nextLine());
                    Pessoa dados = new Pessoa();
                    System.out.print("Nome: ");
                    dados.setNome(scanner.nextLine());
                    System.out.print("Email: ");
                    dados.setEmail(scanner.nextLine());
                    System.out.print("CPF: ");
                    dados.setCpf(scanner.nextLine());
                    System.out.print("Data Nascimento (yyyy-mm-dd): ");
                    dados.setDataNascimento(LocalDate.parse(scanner.nextLine()));
                    service.atualizarPessoa(id, dados);
                    break;
                case "4":
                    System.out.print("ID da pessoa: ");
                    service.deletarPessoa(Long.parseLong(scanner.nextLine()));
                    break;
                case "5":
                    System.out.print("ID da pessoa: ");
                    service.buscarPorId(Long.parseLong(scanner.nextLine()))
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Pessoa nao encontrada"));
                    break;
                case "6":
                    System.out.print("CPF: ");
                    service.buscarPorCpf(scanner.nextLine())
                            .ifPresentOrElse(System.out::println, () -> System.out.println("Pessoa nao encontrada"));
                    break;
                case "7":
                    loggingService.getLogs().forEach(System.out::println);
                    break;
                case "8":
                    relacionamentoService.listarPessoas()
                            .forEach(r -> System.out.println("ID: " + r.getId() + ", Nome: " + r.getNome()));
                    break;
                case "9":
                    System.out.print("ID da primeira pessoa: ");
                    Long p1 = Long.parseLong(scanner.nextLine());
                    System.out.print("ID da segunda pessoa: ");
                    Long p2 = Long.parseLong(scanner.nextLine());
                    System.out.print("Tipo de relacionamento: ");
                    String tipo = scanner.nextLine();
                    relacionamentoService.criarRelacao(p1, p2, tipo);
                    break;
                case "10":
                    relacionamentoService.listarRelacoes().forEach(r -> {
                        r.getRelacoes().forEach(rel ->
                                System.out.println(r.getNome() + " --(" + rel.getTipo() + ")-> " + rel.getPessoa().getNome()));
                    });
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }
}