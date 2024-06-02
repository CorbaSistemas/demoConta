# Projeto de Financeiro - Contas

Este projeto tem como objetivo fornecer uma base geográfica que contém o 
cadastro de Continentes, Países, Regiões e Estados, além de um módulo financeiro para o cadastro e gerenciamento de 
Contas. Utiliza diversas tecnologias modernas como Java 17, Docker-Compose, Arquitetura DDD, Flyway, JUnit, Postgres, 
Rest, Spring Boot, JPA, arquivos CSV e Swagger para criar

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento da aplicação.
- **Spring Boot**: Framework para criação de aplicações Java baseadas em Spring.
- **Docker-Compose**: Ferramenta para definir e executar multi-containers Docker.
- **Flyway**: Ferramenta de migração de banco de dados para controle de versões do esquema do banco.
- **JUnit**: Framework de testes para garantir a qualidade e funcionalidade do código.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional utilizado como base de dados.
- **REST**: Arquitetura utilizada para a criação de APIs.
- **Swagger**: Ferramenta para documentação e teste de APIs RESTful.
- **DDD (Domain-Driven Design)**: Abordagem de modelagem de software centrada no domínio do problema.
- **Logging**: Ferramenta para gerar logs personalizados.
- **JUnit**: Ferramenta para executar testes unitários.

## Estrutura do Projeto

O projeto está estruturado de acordo com os princípios de DDD, da seguinte forma:

- `src/main/java`: Contém o código fonte da aplicação.
    - `com.example.demo`
        - `interfaces`: Contém os controladores REST e configurações específicas da aplicação.
        - `domain`: Contém as entidades, repositórios e serviços de domínio.
        - `infra`: Contém as implementações de repositórios e configurações de infraestrutura.
- `src/main/resources`: Contém os recursos da aplicação.
    - `application.properties`: Arquivo de configuração da aplicação.
    - `db/migration`: Contém os scripts de migração do Flyway.
- `src/test/java`: Contém os testes unitários da aplicação.

## Pré-requisitos

- Java 17
- Maven
- Docker e Docker-Compose

## Configuração do Banco de Dados

O projeto utiliza Docker-Compose para configurar e iniciar o banco de dados PostgreSQL.

