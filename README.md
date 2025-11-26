# Back-end – Sistema de Agendamento de Pedidos para Hamburgueria

Este projeto é uma **API RESTful** desenvolvida com **Spring Boot** para gerenciar pedidos de uma hamburgueria, com foco em aprendizado e boas práticas de desenvolvimento backend. O sistema implementa autenticação, controle de acesso, persistência de dados e testes automatizados.

---

## Objetivo

O projeto foi criado para **praticar conceitos essenciais de back-end**, como:

- Construção de APIs RESTful
- Autenticação e autorização JWT
- Boas práticas de design em camadas
- Testes Automatizados
- Documentação de API com Swagger/OpenAPI

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **BCrypt** (hash de senhas)
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (migrações de banco)
- **Springdoc OpenAPI / Swagger**
- MockMvc  (testes de integração)
- **Maven**

---

## Arquitetura do Projeto

O projeto segue uma **arquitetura em camadas (Layered Architecture)**:

- **Controller:** recebe requisições HTTP e retorna respostas JSON
- **Service:** contém a lógica de negócio e validações
- **Repository:** abstração do acesso ao banco de dados com Spring Data JPA
- **Entities & DTOs:** representam dados persistidos e objetos de transferência de dados entre camadas
- **Configurações:** segurança, JWT, filtros, beans do Spring Boot

> Essa estrutura garante separação de responsabilidades, facilitando manutenção, testes e evolução do sistema.
> 

---

## Funcionalidades

### Pedidos

- **Registrar pedido:** `POST /pedido/registro`
- **Atualizar status do pedido:** `PATCH /pedido/{id}/status`
- **Listar pedidos do usuário:** `GET /pedido/buscar`
- **Listar todos os pedidos:** `GET /pedido/buscar-todos`

### Autenticação

- **Registro de usuário:** `POST /auth/registro`
- **Login:** `POST /auth/login`
- **Rotas protegidas por roles:** `USER` e `ADMIN`

---

## Banco de Dados

- Banco: **PostgreSQL**
- Migrações controladas pelo **Flyway** (criação e atualização do schema automática ao iniciar a aplicação)

---

## Documentação da API

A API está documentada com **Swagger/OpenAPI**:

- Swagger UI: `/swagger-ui/index.html`

Isso permite testar endpoints e visualizar os exemplos de request e response de forma interativa.

---

## Testes

- **Testes de integração** com MockMvc, garantindo que endpoints funcionem corretamente do início ao fim.

---

## Segurança

- Autenticação via **JWT**
- Hash de senhas com **BCrypt**
- Controle de acesso por **roles** (USER e ADMIN)

---

## Conclusão

Este projeto é um **ambiente de estudo e prática** para construção de APIs em Java com Spring Boot, seguindo boas práticas de arquitetura, testes, segurança e documentação.

Ainda está em evolução e estou aberto para opiniões e melhorias, sendo um ótimo ponto de partida para aprender conceitos essenciais de back-end.
