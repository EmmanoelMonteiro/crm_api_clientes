# CRM API Clientes

Uma API RESTful para gerenciamento de informações de clientes, permitindo operações CRUD (Criar, Ler, Atualizar, Excluir) e utilizando um banco de dados em memória H2 para desenvolvimento.

## 🚀 Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e frameworks:

*   **Java**: Versão 21
*   **Spring Boot**: Versão 3.4.1
    *   `spring-boot-starter-web`: Para construção de APIs RESTful.
    *   `spring-boot-starter-data-jpa`: Para persistência de dados com JPA.
    *   `spring-boot-starter-validation`: Para validação de dados de entrada.
    *   `spring-boot-starter-test`: Para testes unitários e de integração.
    *   `spring-boot-devtools`: Ferramentas para agilizar o desenvolvimento (reload automático, etc.).
*   **Lombok**: Para reduzir o código boilerplate (getters, setters, construtores).
*   **H2 Database**: Banco de dados em memória, ideal para desenvolvimento e testes.
*   **Springdoc OpenAPI UI**: Para geração automática de documentação da API (Swagger UI).
*   **Maven**: Ferramenta de automação de construção e gerenciamento de dependências.

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

*   **Java Development Kit (JDK)**: Versão 21
*   **Apache Maven**: Versão 3.8.x ou superior
*   **Git**: Última versão estável
*   **IDE (Opcional)**: IntelliJ IDEA, VS Code, Eclipse ou outra de sua preferência.

## 🚀 Primeiros Passos

Siga estas instruções para configurar e executar o projeto em sua máquina local.

### Clonando o Repositório

```bash
git clone [https://github.com/EmmanoelMonteiro/crm_api_clientes.git](https://github.com/EmmanoelMonteiro/crm_api_clientes.git)
cd crm_api_clientes
```

## 🗂️ Estrutura do Projeto
A estrutura principal do projeto segue o padrão de aplicações Spring Boot:

```bash
.
├──.mvn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── crm
│   │   │           └── api
│   │   │               ├── controller  (Controladores REST)
│   │   │               ├── model       (Modelos de dados/Entidades JPA)
│   │   │               ├── repository  (Interfaces de repositório JPA)
│   │   │               └── service     (Camada de serviço/lógica de negócio)
│   │   │               └── CrmApiApplication.java (Classe principal da aplicação)
│   │   └── resources   (Arquivos de configuração e recursos)
│   └── test
│       └── java
│           └── com
│               └── crm
│                   └── api (Classes de teste)
└── pom.xml             (Arquivo de configuração do Maven)
└── application.properties (Configurações da aplicação)
```

## 🛠️ Instruções de Construção e Execução
### Construindo o Projeto
Para compilar e empacotar a aplicação, navegue até o diretório raiz do projeto (crm_api_clientes) e execute o seguinte comando Maven:
```bash
mvn clean install
```
Este comando irá baixar todas as dependências e gerar o arquivo JAR executável na pasta `target/.`

### Configuração do Banco de Dados
O projeto utiliza o H2 Database, que é um banco de dados em memória. Por padrão, ele será inicializado automaticamente quando a aplicação for executada, sem a necessidade de configuração externa.

A configuração `spring.jpa.show-sql=true` no `application.properties` garante que as instruções SQL geradas pelo JPA sejam exibidas no console, o que é útil para depuração.

### Executando a Aplicação
Após a construção do projeto, você pode iniciar a aplicação Spring Boot de duas maneiras:

### 1. Via Maven:
```bash
mvn spring-boot:run
```

### 2. Executando o JAR:
```bash
java -jar target/crm-api-0.0.1-SNAPSHOT.jar
```

A aplicação será iniciada na porta padrão do Spring Boot, que é `8080`.

## 🌐 Documentação da API (Swagger UI)
A documentação interativa da API está disponível através do Swagger UI. Após iniciar a aplicação, acesse:
```bash
http://localhost:8080/swagger-ui.html
```
