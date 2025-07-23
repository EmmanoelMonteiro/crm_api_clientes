// URL base da sua API
const BASE_URL = 'http://localhost:8080/v1/cliente';

// Elementos DOM do Formulário
const clienteIdInput = document.getElementById('clienteId');
const clienteNomeInput = document.getElementById('clienteNome');
const clienteCpfInput = document.getElementById('clienteCpf');
const clienteIdadeInput = document.getElementById('clienteIdade');
const clienteEmailInput = document.getElementById('clienteEmail');
const clienteLogradouroInput = document.getElementById('clienteLogradouro');
const clienteNumeroInput = document.getElementById('clienteNumero');
const clienteBairroInput = document.getElementById('clienteBairro');
const clienteCidadeInput = document.getElementById('clienteCidade');
const clienteUfInput = document.getElementById('clienteUf');

// Elementos DOM da Lista/Mensagens
const clientTableBody = document.getElementById('clientTableBody');
const noClientsMessage = document.getElementById('noClientsMessage');
const messagesDiv = document.getElementById('messages');

// Função para exibir mensagens (sucesso/erro/info)
function showMessage(message, type) {
    messagesDiv.textContent = message;
    messagesDiv.className = `messages ${type}`;
    messagesDiv.style.display = 'block';
    setTimeout(() => {
        messagesDiv.style.display = 'none';
    }, 3000); // Esconde a mensagem após 3 segundos
}

// --- Método GET: Buscar e Exibir Todos os Clientes ---
async function fetchClients() {
    try {
        const response = await fetch(BASE_URL);
        if (!response.ok) {
            // Se o status for 404 e a resposta for JSON com mensagem de "não encontrado", trate.
            // Para APIs Spring Boot, 404 geralmente significa que o recurso não foi encontrado,
            // mas para /v1/cliente (GET ALL), um 200 OK com lista vazia é mais comum.
            // No entanto, se seu backend retornar 404 para "nenhum cliente", essa lógica pode ajudar.
            if (response.status === 404) {
                // Tenta ler o corpo como JSON para verificar se há uma mensagem específica
                try {
                    const errorData = await response.json();
                    if (errorData.message && errorData.message.includes("nenhum cliente encontrado")) {
                        renderClients([]); // Renderiza uma lista vazia
                        showMessage("Nenhum cliente cadastrado ainda.", "info");
                        return;
                    }
                } catch (jsonError) {
                    // Ignora erro ao tentar parsear JSON se a resposta 404 não for JSON
                }
            }
            throw new Error(`Erro HTTP! Status: ${response.status}`);
        }
        const clients = await response.json();
        renderClients(clients);
    } catch (error) {
        showMessage(`Erro ao carregar clientes: ${error.message}`, 'error');
        clientTableBody.innerHTML = ''; // Limpa a tabela
        noClientsMessage.style.display = 'block'; // Mostra mensagem de "nenhum cliente"
        noClientsMessage.textContent = 'Não foi possível carregar os clientes. Verifique o servidor.';
    }
}

// Renderiza os clientes na tabela
function renderClients(clients) {
    clientTableBody.innerHTML = ''; // Limpa o corpo da tabela
    if (clients.length === 0) {
        noClientsMessage.style.display = 'block';
        noClientsMessage.textContent = 'Nenhum cliente cadastrado ainda.';
        return;
    } else {
        noClientsMessage.style.display = 'none';
    }

    clients.forEach(client => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${client.id || 'N/A'}</td>
            <td>${client.nome || 'N/A'}</td>
            <td>${client.cpf || 'N/A'}</td>
            <td>${client.uf ? client.uf.toUpperCase() : 'N/A'}</td>
            <td>${client.email || 'N/A'}</td>
            <td>
                <button class="edit" onclick="editarClientePreencherForm(
                    '${client.id}',
                    '${client.nome}',
                    '${client.cpf}',
                    ${client.idade || 0},
                    '${client.email}',
                    '${client.logradouro || ''}',
                    ${client.numero || 0},
                    '${client.bairro || ''}',
                    '${client.cidade || ''}',
                    '${client.uf || ''}'
                )">Editar</button>
                <button class="delete" onclick="excluirCliente('${client.id}')">Excluir</button>
            </td>
        `;
        clientTableBody.appendChild(row);
    });
}

// --- Método POST/PUT: Salvar (Criar ou Atualizar) Cliente ---
async function salvarCliente() {
    const id = clienteIdInput.value;
    const nome = clienteNomeInput.value;
    const cpf = clienteCpfInput.value;
    const idade = parseInt(clienteIdadeInput.value); // Converte para número inteiro
    const email = clienteEmailInput.value;
    const logradouro = clienteLogradouroInput.value;
    const numero = parseInt(clienteNumeroInput.value); // Converte para número inteiro
    const bairro = clienteBairroInput.value;
    const cidade = clienteCidadeInput.value;
    const uf = clienteUfInput.value;

    // Validação básica
    if (!nome || !cpf || isNaN(idade) || !email || !uf) {
        showMessage('Por favor, preencha todos os campos obrigatórios (Nome, CPF, Idade, Email, UF).', 'error');
        return;
    }

    // Estrutura completa dos dados conforme seu backend espera
    const clientData = {
        nome: nome,
        cpf: cpf,
        idade: idade,
        email: email,
        logradouro: logradouro,
        numero: isNaN(numero) ? 0 : numero, // Garante que é número, 0 se vazio
        bairro: bairro,
        cidade: cidade,
        uf: uf.toLowerCase() // Envia em minúsculas se seu backend espera assim
    };

    let url = BASE_URL;
    let method = 'POST';

    if (id) { // Se o ID existe, é uma edição (PUT)
        url = `${BASE_URL}`;
        method = 'PUT';
        clientData.id = id; // O backend espera o ID para PUT
    }

    console.log(method);
    console.log(clientData);

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clientData) // Converte o objeto JavaScript em string JSON
        });

        if (!response.ok) {
            const errorData = await response.json(); // Tenta ler o erro como JSON
            throw new Error(`Erro HTTP! Status: ${response.status} - ${errorData.message || 'Erro desconhecido.'}`);
        }

        const result = await response.json(); // Backend deve retornar o objeto criado/atualizado
        showMessage(`Cliente ${id ? 'atualizado' : 'criado'} com sucesso! ID: ${result.id}`, 'success');
        limparFormulario();
        fetchClients(); // Recarrega a lista
    } catch (error) {
        showMessage(`Erro ao salvar cliente: ${error.message}`, 'error');
    }
}

// Preenche o formulário para edição
function editarClientePreencherForm(id, nome, cpf, idade, email, logradouro, numero, bairro, cidade, uf) {
    clienteIdInput.value = id;
    clienteNomeInput.value = nome;
    clienteCpfInput.value = cpf;
    clienteIdadeInput.value = idade;
    clienteEmailInput.value = email;
    clienteLogradouroInput.value = logradouro;
    clienteNumeroInput.value = numero;
    clienteBairroInput.value = bairro;
    clienteCidadeInput.value = cidade;
    clienteUfInput.value = uf;

    window.scrollTo({ top: 0, behavior: 'smooth' }); // Rola para o topo para o formulário
}

// Limpa o formulário
function limparFormulario() {
    clienteIdInput.value = '';
    clienteNomeInput.value = '';
    clienteCpfInput.value = '';
    clienteIdadeInput.value = '';
    clienteEmailInput.value = '';
    clienteLogradouroInput.value = '';
    clienteNumeroInput.value = '';
    clienteBairroInput.value = '';
    clienteCidadeInput.value = '';
    clienteUfInput.value = '';
}

// --- Método DELETE: Excluir Cliente ---
async function excluirCliente(id) {
    if (!confirm(`Tem certeza que deseja excluir o cliente com ID ${id}?`)) {
        return;
    }

    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE'
        });

        if (response.status === 204) { // No Content para DELETE bem-sucedido
            showMessage(`Cliente ${id} excluído com sucesso!`, 'success');
        } else if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`Erro HTTP! Status: ${response.status} - ${errorText}`);
        } else { // Backend retornou 200 OK com algum conteúdo
            const result = await response.json();
            showMessage(`Cliente ${id} excluído com sucesso!`, 'success');
        }
    } catch (error) {
        showMessage(`Erro ao excluir cliente ${id}: ${error.message}`, 'error');
    }
    
    fetchClients(); // Recarrega a lista
}

// Carrega os clientes ao carregar a página
document.addEventListener('DOMContentLoaded', fetchClients);