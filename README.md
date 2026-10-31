# Chatbot API

API REST de um chatbot desenvolvido com Java e Spring Boot, utilizando o Spring AI para integração com o Ollama e PostgreSQL para persistência das conversas.

O projeto está sendo desenvolvido com foco no aprendizado de desenvolvimento backend e construção de APIs REST.

## Tecnologias

- Java 
- Spring Boot
- Spring AI
- Ollama (llama3.2:1b)
- PostgreSQL 
- Spring Data JPA
- Hibernate
- Lombok
- Docker e Docker Compose

## Funcionalidades

- Comunicação com um modelo de IA local através do Ollama.
- Endpoint REST para envio de mensagens.
- Criação e persistência de conversas.
- Armazenamento das mensagens do usuário e do assistente.
- Associação de mensagens às respectivas conversas.

## Como executar

**1. Clone o repositório**

```bash
git clone https://github.com/gabrieltsusato/chatbot-api.git
cd chatbot-api
```

**2. Configure as variáveis de ambiente**

Copie o arquivo `.env.example` para `.env` e configure as credenciais do PostgreSQL e pgAdmin.

**3. Inicie o banco de dados**

```bash
docker compose up -d
```

**4. Prepare o Ollama**

Instale o Ollama e baixe o modelo utilizado:

```bash
ollama pull llama3.2:1b
```

Certifique-se de que o servidor Ollama esteja em execução.

**5. Configure as variáveis de ambiente da aplicação**

Configure `POSTGRES_USER` e `POSTGRES_PASSWORD` no ambiente de execução do Spring Boot.

**6. Execute a aplicação**

Execute a classe principal do projeto pela IDE ou utilize:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

## Endpoint

`POST /api/chat`

Exemplo de requisição:

```json
{
  "mensagem": "Quanto é 10 + 1?"
}
```

Para continuar uma conversa existente, envie também o `conversationId`:

```json
{
  "mensagem": "E quanto é 20 + 5?",
  "conversationId": 1
}
```

## Objetivo

Projeto pessoal desenvolvido para praticar Java, Spring Boot, APIs REST, integração com inteligência artificial, persistência de dados e Docker.
