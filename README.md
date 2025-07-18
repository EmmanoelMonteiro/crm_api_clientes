# CRM API de Clientes

API para gerenciamento de clientes em um sistema CRM (Customer Relationship Management).

## 📋 Requisitos

- Node.js (versão 18 ou superior)
- npm ou yarn
- Banco de dados PostgreSQL
- Docker (opcional, para execução em container)

## 🛠️ Instalação

### 1. Clone o repositório:
```bash
git clone https://github.com/EmmanoelMonteiro/crm_api_clientes.git
cd crm_api_clientes
```

### 2. Instale as dependências:
```bash
npm install
# ou
yarn install
```

### 3. Configure as variáveis de ambiente:
Crie um arquivo .env na raiz do projeto baseado no .env.example:
```bash
DATABASE_URL=postgresql://usuario:senha@localhost:5432/nome_banco
PORT=3000
JWT_SECRET=sua_chave_secreta_jwt
```


## 🗂️ Estrutura do Projeto
```bash
crm_api_clientes/
├── src/
│   ├── controllers/    # Lógica dos endpoints
│   ├── models/         # Definições dos modelos de dados
│   ├── routes/         # Definição das rotas
│   ├── services/       # Lógica de negócio
│   ├── utils/          # Utilitários e helpers
│   ├── app.js          # Configuração do Express
│   └── server.js       # Inicialização do servidor
├── migrations/         # Migrações do banco de dados
├── tests/              # Testes automatizados
├── .env.example        # Exemplo de variáveis de ambiente
├── package.json        # Dependências e scripts
└── README.md           # Este arquivo
```

## ▶️ Execução
```bash
npm run dev
# ou
yarn dev
```

## 📚 Rotas da API

| Método	| Rota	| Descrição|
| ---	| ---	| ---|
| POST	| /api/clientes	| Cria um novo cliente|
| GET	| /api/clientes	| Lista todos os clientes|
| GET	| /api/clientes/:id	| Obtém um cliente específico|
| PUT	| /api/clientes/:id	| Atualiza um cliente|
| DELETE	| /api/clientes/:id	| Remove um cliente|
