document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById('login-form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const email = document.getElementById('email').value;
        const senha = document.getElementById('password').value;

        const loginData = {
            username: email,
            password: senha,
        };

        fetch('http://localhost:8080/api/clientes/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(loginData),
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(erro => {
                    throw new Error(erro.message || 'Usuário ou senha inválidos');
                });
            }
            return response.json();
        })
        .then(data => {
            console.log('Login bem-sucedido:', data);
            showSnackbar('Login realizado com sucesso!');

            setTimeout(() => {
                window.location.href = '../index.html';
            }, 3000);
        })
        .catch(error => {
            console.error('Erro:', error);
            showSnackbar(`Erro: ${error.message}`);
        });
    });

    function showSnackbar(message) {
        const snackbar = document.getElementById("snackBar");
        snackbar.textContent = message;
        snackbar.className = "snackBar show";
        setTimeout(() => {
            snackbar.className = snackbar.className.replace("show", "");
        }, 3000);
    }
});
