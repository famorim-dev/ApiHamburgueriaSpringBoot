# Back-end – Sistema de Agendamento de Pedidos para Hamburgueria

Este projeto foi desenvolvido com foco em aprendizado e prática de construção de APIs com Spring Boot. A ideia é simular um fluxo simples de pedidos em uma hamburgueria, trabalhando autenticação, controle de acesso e organização do back-end.

---

## Tecnologias Utilizadas
- Java  
- Spring Boot  
- Spring Security + JWT  
- PostgreSQL  
- Flyway

---

## Funcionalidades
- Registrar pedidos  
- Cancelar pedidos  
- Listar pedidos do usuário  
- Autenticação com JWT  
- Rotas protegidas por roles (USER e ADMIN)

---

## Arquitetura do Projeto
- **Controllers:** recebem e respondem às requisições  
- **Services:** regra de negócio  
- **Repositories:** acesso ao banco com Spring Data JPA  
- **Entities e DTOs:** organização e separação de responsabilidades  
- **Configurações:** segurança, filtros e JWT

---

## Banco de Dados
O projeto utiliza PostgreSQL.  
As migrações são controladas pelo Flyway para criar e atualizar o schema automaticamente ao iniciar a aplicação.

---

## Objetivo
Este é um projeto de estudo, criado para praticar conceitos essenciais de back-end, segurança e boas práticas. Ainda está em evolução e aberto para melhorias.
