document.addEventListener('DOMContentLoaded', function() {
    const apiUrl = 'http://localhost:8080/pessoa';
    const form = document.getElementById('pessoaForm');
    const pessoasList = document.getElementById('pessoasList');

    function listarPessoas() {
        fetch(apiUrl)
        .then(response => response.json())
        .then(pessoas => {
            pessoasList.innerHTML = '';
            pessoas.forEach(pessoa => {
                const li = document.createElement('li');
                li.textContent = `Nome: ${pessoa.nome} - CPF: ${pessoa.cpf} - Numero: ${pessoa.numero}`;
                const liLinha = document.createElement('hr');
                liLinha.classList.add('linha');
                pessoasList.appendChild(li);
                pessoasList.appendChild(liLinha);
            });
        });
    }
    
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        const nome = document.getElementById('nome').value;
        const cpf = document.getElementById('cpf').value;
        const numero = document.getElementById('numero').value;
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nome, cpf, numero })
        })
        .then(response => response.json())
        .then(() => {
            listarPessoas();
            form.reset();
        })
        .catch(error => console.error('Erro:', error));
    });
    listarPessoas();
});
