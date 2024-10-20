document.addEventListener("DOMContentLoaded", function() {
    fetch("http://localhost:8080/api/produtos/listar")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar produtos");
            }
            return response.json();
        })
        .then(produtos => {
            const cardsContainer = document.querySelector('.cards-container');
            cardsContainer.innerHTML = '';

            produtos.forEach(produto => {
                const card = document.createElement('div');
                card.classList.add('card');
                card.style.width = '30rem';
                card.style.border = 'none';
                card.innerHTML = `
                    <img src="${produto.imagem}" class="card-img-top" alt="imagem do produto">
                    <div class="card-body">
                        <h5 class="card-title">${produto.nome}</h5>
                        <p class="card-text">R$ ${produto.valor.toFixed(2)}</p>
                        <a href="#" class="btn btn-primary buy_button"
                           style="background-color: rgb(143, 11, 11); border: none; width: 12rem; height: 3.8rem; font-size: 1.4rem; padding-top: 7px;">
                           Comprar
                        </a>
                    </div>
                `;


                const buyButton = card.querySelector('.buy_button');
                buyButton.addEventListener('click', function(event) {
                    event.preventDefault();
                    const produtosComprados = JSON.parse(localStorage.getItem('produtosComprados')) || [];
                    produtosComprados.push(produto);
                    localStorage.setItem('produtosComprados', JSON.stringify(produtosComprados));
                    showSnackbar(`${produto.nome} foi adicionado ao carrinho!`);
                });

                cardsContainer.appendChild(card);
            });
        })
        .catch(error => {
            console.error("Erro:", error);
        });
});


function showSnackbar(message) {
    const snackbar = document.getElementById("snackbar");
    snackbar.textContent = message;
    snackbar.className = "show";
    setTimeout(() => {
        snackbar.className = snackbar.className.replace("show", "");
    }, 3000);
}
