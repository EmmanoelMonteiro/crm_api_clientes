# CRM API Clientes

Uma API RESTful para gerenciamento de informações de clientes, permitindo operações CRUD (Criar, Ler, Atualizar, Excluir) e utilizando um banco de dados em memória H2 para desenvolvimento.

## 🚀 Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e frameworks:

### Backend
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

### Frontend
* **HTML5**: Estrutura e conteúdo da interface do usuário.
* **CSS3**: Estilização e layout da interface para uma experiência visual agradável.
* **JavaScript (ES6+)**: Lógica interativa, manipulação do DOM e comunicação assíncrona com a API (utilizando `fetch` API).
* **Node.js**: Ambiente de execução para ferramentas de desenvolvimento do frontend.
* **`serve` (NPM Package)**: Um servidor HTTP simples para servir os arquivos estáticos do frontend em ambiente de desenvolvimento.

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

*   **Java Development Kit (JDK)**: Versão 21
*   **Apache Maven**: Versão 3.8.x ou superior
*   **Git**: Última versão estável
*   **IDE (Opcional)**: IntelliJ IDEA, VS Code, Eclipse ou outra de sua preferência.
*   **Node.js e npm (Node Package Manager)**: Versão 16.x ou superior (necessário para o ambiente de desenvolvimento do frontend).

## 🚀 Primeiros Passos

Siga estas instruções para configurar e executar o projeto em sua máquina local.

### Clonando o Repositório

```bash
git clone https://github.com/EmmanoelMonteiro/crm_api_clientes.git
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
 │                       └── CrmApiApplicationTests.java (Classe de testes da aplicação)
+├── front-end/               (Módulo do Frontend)
+│   ├── node_modules/        (Dependências do Node.js, geradas automaticamente)
+│   ├── public/              (Arquivos estáticos do frontend para serem servidos)
+│   │   ├── css/             (Estilos CSS da aplicação)
+│   │   │   └── style.css
+│   │   ├── js/              (Lógica JavaScript da aplicação)
+│   │   │   └── script.js
+│   │   └── index.html       (Página HTML principal da aplicação)
+│   ├── package.json         (Metadados e scripts de dependências do Node.js)
+│   └── package-lock.json    (Bloqueia versões de dependências)
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

## 🌐 Documentação da API (Swagger UI)
A documentação interativa da API está disponível através do Swagger UI. Após iniciar a aplicação, acesse:
```bash
http://localhost:8080/swagger-ui.html
```

+### 🚀 Executando as Aplicações

Para que o sistema funcione corretamente, é necessário executar tanto o backend (API) quanto o frontend (interface web).

#### 1. Iniciando o Backend (API Spring Boot)

Navegue até o diretório raiz do projeto (`crm_api_clientes`).

Após a construção do projeto (`mvn clean install`), você pode iniciar a aplicação Spring Boot de duas maneiras:

**a. Via Maven:**
```bash
   mvn spring-boot:run
   ```
**b. Executando o JAR:**
```bash
   java -jar target/crm-api-0.0.1-SNAPSHOT.jar
   ```
A API será iniciada na porta padrão do Spring Boot, que é `8080`.

#### 2. Iniciando o Frontend (Interface Web)

Em um **novo terminal**, navegue até o diretório `front-end` dentro do projeto:

```bash
   cd front-end
   ```

**a. Instale as dependências (somente na primeira vez ou quando o `package.json` for alterado):**
```bash
   npm install
   ```

**b. Inicie o servidor de desenvolvimento do frontend:**
```bash
   npm start
   ```
(Este comando executa `npx serve public` conforme configurado no `package.json` do frontend).

O frontend será iniciado em `http://localhost:3000` (ou outra porta disponível, verifique o console do terminal).

### 🔗 Acessando a Aplicação

Após ambos os servidores (backend e frontend) estarem em execução:

* **Frontend (Aplicação Web):** Acesse `http://localhost:3000` (ou a porta indicada pelo `npm start`) em seu navegador.
* **Backend (API RESTful):** A API estará respondendo requisições na porta `8080`.
* **Documentação da API (Swagger UI):** Acesse `http://localhost:8080/swagger-ui.html` para explorar os endpoints da API.