# Catálogo de Livros - Desafio LiterAlura

Bem-vindo ao projeto de Catálogo de Livros! Este é um aplicativo desenvolvido em Java usando o framework Spring Boot proporcionado pela Alura. A aplicação permite buscar livros pelo título, listar livros registrados, listar autores registrados e listar autores vivos em um determinado ano.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 2.7.1**
- **PostgreSQL**
- **Lombok**
- **Jackson**


## Funcionalidades

### 1. Buscar Livro pelo Título

Permite buscar um livro pelo título utilizando a API do Gutendex. Os dados do livro e do autor são salvos no banco de dados se ainda não existirem.

### 2. Listar Livros Registrados

Exibe todos os livros registrados no banco de dados.

### 3. Listar Autores Registrados

Exibe todos os autores registrados no banco de dados.

### 4. Listar Autores Vivos em um Determinado Ano

Permite listar os autores que estavam vivos em um determinado ano.

### 0. Sair

Encerra a aplicação.

1. Clone o repositório:
    ```bash
    git clone https://github.com/Sav1o/Desafio_LiterAlura.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd Desafio_alura_Literatura
    ```

3. Configure o banco de dados no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco_de_dados
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Execute a aplicação:
    ```bash
    ./mvnw spring-boot:run
    ```

5. A aplicação será iniciada e você poderá interagir com ela através do terminal.

