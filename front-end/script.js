const API_URL = 'http://localhost:8080/funcionarios';
let token = '';


function showLoginModal() {
    document.getElementById('loginModal').classList.remove('hidden');
}


document.getElementById('loginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });

    if (response.ok) {
        const data = await response.text();

        if (data && data.includes('.')) {
            token = data;
            localStorage.setItem('jwt_token', token);
            document.getElementById('loginModal').classList.add('hidden');
            await fetchFuncionarios();
        } else {
            alert('Token inválido recebido!');
        }
    } else {
        alert('Credenciais inválidas');
    }
});

async function fetchFuncionarios() {
    const response = await fetch(API_URL, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        },
    });

    if (response.ok) {
        const funcionarios = await response.json();
        renderFuncionarios(funcionarios);
    } else {
        alert('Erro ao buscar dados');
    }
}

function renderFuncionarios(funcionarios) {
    const tbody = document.getElementById('funcionarios-tbody');
    tbody.innerHTML = '';
    funcionarios.forEach(funcionario => {
        const tr = document.createElement('tr');
        tr.classList.add('text-center');
        tr.innerHTML = `
            <td class="px-4 py-2 border">${funcionario.id}</td>
            <td class="px-4 py-2 border">${funcionario.nome}</td>
            <td class="px-4 py-2 border">${funcionario.funcao}</td>
            <td class="px-4 py-2 border">${'R$ ' + funcionario.salario}</td>
            <td class="px-4 py-2 border">${funcionario.dataNascimento}</td>
        `;
        tbody.appendChild(tr);
    });
}

document.getElementById('aumentarSalarioBtn').addEventListener('click', async function() {
    const percentual = prompt("Digite o percentual para aumentar:");

    if (percentual) {
        const response = await fetch(`http://localhost:8080/funcionarios/aumentar-salario?percentual=${percentual}`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
                'Content-Type': 'application/json',
            }
        });

        if (response.ok) {
            alert(`${percentual}% aumentado com sucesso!`);
            await fetchFuncionarios();
        } else {
            alert('Erro ao aumentar salário. Tente novamente.');
        }
    }
});


document.getElementById('deletarFuncionarioBtn').addEventListener('click', async function() {
    const nome = prompt("Digite o nome do funcionário que deseja deletar:");

    if (nome) {
        const response = await fetch(`http://localhost:8080/funcionarios/${nome}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
                'Content-Type': 'application/json',
            }
        });

        if (response.ok) {
            alert(`Funcionário ${nome} deletado com sucesso!`);
            await fetchFuncionarios();
        } else {
            alert('Erro ao deletar funcionário. Verifique o nome ou tente novamente.');
        }
    }
});

document.getElementById('addFuncionarioForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const nome = document.getElementById('nome').value;
    const funcao = document.getElementById('funcao').value;
    const salario = document.getElementById('salario').value;
    const dataNascimento = document.getElementById('dataNascimento').value;

    const funcionario = { nome, funcao, salario, dataNascimento };

    await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(funcionario),
    });

    alert('Funcionário criado');
    await fetchFuncionarios();
});


document.getElementById('agruparFuncaoBtn').addEventListener('click', async function () {
    const response = await fetch('http://localhost:8080/funcionarios/agrupar-por-funcao', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const agrupamento = await response.json();
        renderGroupedByFunction(agrupamento);
    } else {
        alert('Erro ao agrupar funcionários por função');
    }
});

function renderGroupedByFunction(agrupamento) {
    const tbody = document.getElementById('funcionarios-tbody');
    tbody.innerHTML = '';

    for (const [funcao, funcionarios] of Object.entries(agrupamento)) {
        const tr = document.createElement('tr');
        tr.classList.add('text-center', 'font-bold');
        tr.innerHTML = `<td colspan="5" class="px-4 py-2 border">${funcao}</td>`;
        tbody.appendChild(tr);

        funcionarios.forEach(funcionario => {
            const trFuncionario = document.createElement('tr');
            trFuncionario.classList.add('text-center');
            trFuncionario.innerHTML = `
                <td class="px-4 py-2 border">${funcionario.id}</td>
                <td class="px-4 py-2 border">${funcionario.nome}</td>
                <td class="px-4 py-2 border">${funcionario.funcao}</td>
                <td class="px-4 py-2 border">${funcionario.salario}</td>
                <td class="px-4 py-2 border">${formatDate(funcionario.dataNascimento)}</td>
            `;
            tbody.appendChild(trFuncionario);
        });
    }
}

function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
}

function renderFuncionariosAlfabetica(funcionarios) {
    const tbody = document.getElementById('funcionarios-tbody');
    tbody.innerHTML = '';

    funcionarios.forEach(funcionario => {
        const tr = document.createElement('tr');
        tr.classList.add('text-center');

        tr.innerHTML = `
            <td class="px-4 py-2 border">${funcionario.id}</td>
            <td class="px-4 py-2 border">${funcionario.nome}</td>
            <td class="px-4 py-2 border">${funcionario.funcao}</td>
            <td class="px-4 py-2 border">${funcionario.salario}</td>
            <td class="px-4 py-2 border">${formatDate(funcionario.dataNascimento)}</td>
        `;
        tbody.appendChild(tr);
    });
}

document.getElementById('maisVelhoBtn').addEventListener('click', async function () {
    const response = await fetch('http://localhost:8080/funcionarios/mais-velho', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const funcionarioMaisVelho = await response.json();
        console.log(funcionarioMaisVelho);
        alert(`Funcionário mais velho: ${JSON.stringify(funcionarioMaisVelho, null, 2)}`);
    } else {
        alert('Erro ao obter o funcionário mais velho');
    }
});

document.getElementById('aniversariantesBtn').addEventListener('click', async function () {
    const response = await fetch('http://localhost:8080/funcionarios/aniversariantes-out-dez', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const aniversariantes = await response.json();
        console.log(aniversariantes);
        alert(`Aniversariantes de outubro e dezembro: ${JSON.stringify(aniversariantes, null, 2)}`);
    } else {
        alert('Erro ao listar aniversariantes');
    }
});

document.getElementById('ordenadosBtn').addEventListener('click', async function () {
    const response = await fetch('http://localhost:8080/funcionarios/ordenados', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const funcionariosOrdenados = await response.json();
        renderFuncionariosAlfabetica(funcionariosOrdenados);
    } else {
        alert('Erro ao ordenar funcionários');
    }
});

document.getElementById('totalSalariosBtn').addEventListener('click', async function () {
    const response = await fetch('http://localhost:8080/funcionarios/total-salarios', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const totalSalarios = await response.json();
        console.log(totalSalarios);
        alert(`Total de salário: ${'R$ ' + JSON.stringify(totalSalarios, null, 2)}`);
    } else {
        alert('Erro ao calcular total dos salários');
    }
});

document.getElementById('salariosMinimosBtn').addEventListener('click', async function () {
    const response = await fetch('http://localhost:8080/funcionarios/quantidade-salarios-minimos', {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
            'Content-Type': 'application/json',
        }
    });

    if (response.ok) {
        const salariosMinimos = await response.json();
        console.log(salariosMinimos);
        alert(`Quantidade de salários minímos por funcionário: ${JSON.stringify(salariosMinimos, null, 2)}`);
    } else {
        alert('Erro ao calcular salários mínimos');
    }
});

document.getElementById('deletarFuncionarioBtn').addEventListener('click', async function () {
    const nome = prompt("Digite o nome do funcionário que deseja deletar:");

    if (nome) {
        const response = await fetch(`http://localhost:8080/funcionarios/${nome}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('jwt_token')}`,
                'Content-Type': 'application/json',
            }
        });

        if (response.ok) {
            alert(`Funcionário ${nome} deletado com sucesso!`);
            fetchFuncionarios();
        } else {
            alert('Erro ao deletar funcionário. Verifique o nome ou tente novamente.');
        }
    }
});


document.addEventListener('DOMContentLoaded', function() {
    const storedToken = localStorage.getItem('jwt_token');
    if (storedToken) {
        console.log('aqui', token)
        token = storedToken;
        fetchFuncionarios();
    } else {
        showLoginModal();
    }
});
