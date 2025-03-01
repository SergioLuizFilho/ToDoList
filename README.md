# Sistema de Gerenciamento de Tarefas e Usuários

Este projeto consiste em um sistema de gerenciamento de tarefas e usuários baseado em linha de comando. Ele foi desenvolvido utilizando **Spring Boot** e **Spring Data JPA**, tendo **MySQL** como banco de dados.

## Funcionalidades

- Cadastro de usuários
- Cadastro de tarefas associadas aos usuários
- Listagem e gerenciamento das tarefas
- Atualização e remoção de tarefas
- Consulta de tarefas pendentes e concluídas

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- **Camada de Modelo**: Contém as classes que representam as entidades do sistema, como Usuário e Tarefa.
- **Camada de Repositório**: Responsável pela comunicação com o banco de dados usando Spring Data JPA.
- **Camada de Serviço**: Implementa as regras de negócio e lógica do sistema.
- **Camada de Controle**: Gerencia a interação do usuário com o sistema via linha de comando.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Hibernate**

## Funcionalidades Implementadas

- Cadastro, edição e remoção de usuários
- Cadastro, edição e remoção de tarefas
- Associação de tarefas a usuários
- Filtragem de tarefas por status (pendente/concluída)
- Persistência de dados utilizando MySQL e Hibernate

As funcionalidades foram implementadas seguindo boas práticas de desenvolvimento com Java e utilizando um banco relacional para persistência dos dados.
