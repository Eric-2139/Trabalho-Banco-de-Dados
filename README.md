# Trabalho Banco de Dados

Este repositório contém um projeto de exemplo utilizando Spring Boot para demonstração de integração com diferentes tecnologias de banco de dados.

## Tecnologias Utilizadas
- Spring Boot 3
- JPA/Hibernate (PostgreSQL)
- MongoDB
- Neo4j
- Redis
- Cache com Spring
- JUnit 5 para testes

## Requisitos
- Java 17 ou superior
- Maven Wrapper (incluído no repositório)

## Como executar
1. Clone o repositório.
2. Compile e execute a aplicação usando o Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
3. A aplicação será iniciada na porta padrão 8080.

## Executando os testes
Para rodar os testes automatizados:
```bash
./mvnw test
```

## Estrutura do Projeto
```
src/
  main/
    java/com/example/trabalhobancodedados/  - Código fonte principal
  test/
    java/com/example/trabalhobancodedados/  - Testes unitários
```

Este projeto serve apenas como ponto de partida para estudos e pode ser expandido conforme a necessidade.
