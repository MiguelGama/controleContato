# controleContato

# Controle Contato


## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- JPA/Hibernate
- Oracle Database

## Configuração do Projeto

Passos para configurar e rodar o projeto localmente.

1. Clone o repositório para a sua máquina local.
2. Configure o banco de dados Oracle e atualize o arquivo `application.properties` com as credenciais corretas.
3. Rode o comando `mvn clean install` para construir o projeto.
4. execute na sua ide, preferencialmente o intellij.

## Funcionalidades

Funcionalidades do projeto.

- CRUD de contatos
- Busca de endereço por CEP

## Endpoints da API

Liste os endpoints da sua API e o que cada um faz. Por exemplo:

- `GET /api/contato`: retorna todos os contatos
- `GET /api/contato/{id}`: retorna o contato com o ID especificado
- `POST /api/contato`: cria um novo contato
```json
{
    "email": "test@example.com",
    "nome": "Test",
    "telefone": "1234567890",
    "cep": "65340-000",
    "dataCadastro": "dd/MM/yyyy HH:mm:ss"
}
```
- `PUT /api/contato/{id}`: atualiza o contato com o ID especificado
- `DELETE /api/contato/{id}`: deleta o contato com o ID especificado

## TABELA CONTATO

```sql

CREATE TABLE tb_contato (
    id NUMBER(19) PRIMARY KEY,
    email VARCHAR2(255),
    nome VARCHAR2(255),
    telefone VARCHAR2(255),
    cep VARCHAR2(255),
    endereco VARCHAR2(255),
    cidade VARCHAR2(255),
    uf VARCHAR2(255),
    data_cadastro TIMESTAMP
);

```
