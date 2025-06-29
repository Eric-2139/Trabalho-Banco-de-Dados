package com.example.trabalhobancodedados.cli;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.trabalhobancodedados.model.Pessoa;
import com.example.trabalhobancodedados.service.PessoaService;

@Component
public class PessoaCommandLineRunner implements CommandLineRunner {

    private final PessoaService service;

    public PessoaCommandLineRunner(PessoaService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1 - Criar pessoa");
            System.out.println("2 - Listar pessoas");
            System.out.println("3 - Atualizar pessoa");
            System.out.println("4 - Deletar pessoa");
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
                case "0":
                    return;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }
}