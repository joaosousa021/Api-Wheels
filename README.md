# Api-Wheels

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.3-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)
![Database](https://img.shields.io/badge/Azure_SQL-blueviolet)

## ⚠️ Status do Projeto

**Projeto concluído. A API não está mais em produção.**

Esta aplicação foi desenvolvida como um projeto acadêmico. O banco de dados no **Azure SQL**, que servia de base para a API, foi desativado após a conclusão para evitar custos de manutenção. O código, no entanto, é totalmente funcional e serve como uma demonstração das minhas habilidades em desenvolvimento back-end com Java e Spring Boot. Para executar localmente, basta configurar uma nova conexão de banco de dados (veja a seção "Como Executar").

## 📄 Sobre o Projeto

**Api-Wheels** é uma API RESTful desenvolvida em Java com o framework Spring Boot. O projeto foi criado do absoluto zero para gerenciar as operações de uma **loja de aluguel de bicicletas**.

O sistema foi projetado para consumir e manipular dados de múltiplas tabelas simultaneamente, gerenciando entidades essenciais do negócio como `Clientes`, `Bicicletas` (modeladas como `Veiculo`), `Funcionários` e `Agendamentos` de aluguel, expondo endpoints para operações de CRUD (Create, Read, Update, Delete) e outras consultas específicas.

## 🚀 Principal Desafio

O maior desafio deste projeto foi a engenharia reversa e a compreensão de um banco de dados já existente no Azure SQL. Foi necessário analisar as tabelas, seus relacionamentos complexos e os dados para modelar corretamente as entidades (`@Entity`) e repositórios (`JpaRepository`). O objetivo foi garantir uma integração robusta e performática, permitindo que a API realizasse consultas que envolviam diversas tabelas de forma eficiente.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.3.3
* **Banco de Dados:** Azure SQL (Infraestrutura original do projeto)
* **ORM:** Spring Data JPA (Hibernate)
* **Gerenciador de Dependências:** Maven
* **Servidor Web:** Tomcat Embutido

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura baseada em camadas para garantir a separação de responsabilidades:

-   `src/main/java/com/api/wheels`
    -   `controller`: Camada responsável por expor os endpoints da API (ex: `AgendamentoController`, `ClienteController`).
    -   `model`: Contém as entidades JPA que mapeiam as tabelas do banco de dados (ex: `Agendamento`, `Cliente`, `Veiculo` representando uma bicicleta).
    -   `repository`: Interfaces do Spring Data JPA para abstrair o acesso aos dados.

## ⚙️ Como Executar

Como a base de dados original foi desativada, para rodar este projeto você precisará de uma instância de banco de dados SQL (pode ser SQL Server, MySQL, PostgreSQL, etc.).

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/joaosousa021/Api-Wheels.git](https://github.com/joaosousa021/Api-Wheels.git)
    ```
2.  **Configure o Banco de Dados:**
    -   Acesse o arquivo `src/main/resources/application.properties`.
    -   Altere as propriedades `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com as credenciais do **seu** banco de dados local ou em nuvem.
    -   **Importante:** Talvez seja necessário ajustar a propriedade `spring.jpa.database-platform` para o dialeto do seu banco de dados (ex: `org.hibernate.dialect.MySQLDialect`).

3.  **Execute a aplicação:**
    -   Utilizando uma IDE (IntelliJ, Eclipse), localize a classe `WheelsApplication.java` e execute-a. O Spring JPA (`hibernate.ddl-auto=update`) tentará criar as tabelas com base nas suas classes de modelo.
    -   Ou, pela linha de comando, utilizando o Maven:
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em `http://localhost:8080`.

---
*Projeto desenvolvido por João Sousa.*
