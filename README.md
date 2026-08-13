# TicketHub API

API RESTful para gerenciamento e venda de ingressos desenvolvida com Kotlin, Spring Boot e PostgreSQL. O projeto conta com persistência via Spring Data JPA, gerenciamento de transações com `@Transactional`, validação de DTOs e tratamento centralizado de exceções.

## Tecnologias

* Kotlin
* Spring Boot 3 (Web, Data JPA, Validation)
* PostgreSQL
* Gradle (Kotlin DSL)

## Arquitetura do Projeto

A aplicação segue uma estrutura em camadas organizadas por responsabilidades:

* `controller`: Exposição dos endpoints REST e recebimento das requisições.
* `service`: Regras de negócio e controle transacional.
* `domain.entities`: Entidades JPA mapeadas para o banco de dados.
* `dto`: Objetos de transferência de dados para entrada e validação.
* `repository`: Interfaces do Spring Data JPA para acesso ao banco.
* `exceptions`: Trata as exceções da aplicação de forma centralizada usando `@RestControllerAdvice`.

## Como Rodar Localmente

### Pré-requisitos
* Java 21
