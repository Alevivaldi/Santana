document.addEventListener('DOMContentLoaded', () => {
    carregandoCarrinho();

    const checkoutButton = document.querySelector('.checkout-btn');
    checkoutButton.addEventListener('click', finalizePurchase);
});

function carregandoCarrinho() {
    const cartItemsContainer = document.getElementById('cart-items');
    const totalPrecoElemento = document.getElementById('total-price');
    const produtosComprados = JSON.parse(localStorage.getItem('produtosComprados')) || [];

    cartItemsContainer.innerHTML = '';
    let total = 0;

    produtosComprados.forEach(produto => {
        const cartItem = document.createElement('div');
        cartItem.classList.add('cart-item');
        cartItem.innerHTML = `
            <img src=".${produto.imagem}" alt="${produto.nome}" class="cart-item-image">
            <div class="cart-item-details">
                <h2>${produto.nome}</h2>
                <p class="price">R$ ${produto.valor.toFixed(2)}</p>
                <input type="number" value="1" min="1" class="quantity-input">
                <button class="remove-btn" data-nome="${produto.nome}">Remover</button>
            </div>
        `;
        cartItemsContainer.appendChild(cartItem);
        total += produto.valor;
    });

    totalPrecoElemento.textContent = total.toFixed(2);

    const removeButtons = document.querySelectorAll('.remove-btn');
    removeButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            const nome = event.target.getAttribute('data-nome');
            removeFromCart(nome);
        });
    });
}

function removeFromCart(nome) {
    let produtosComprados = JSON.parse(localStorage.getItem('produtosComprados')) || [];
    produtosComprados = produtosComprados.filter(produto => produto.nome !== nome);
    localStorage.setItem('produtosComprados', JSON.stringify(produtosComprados));
    carregandoCarrinho();
}

function finalizePurchase() {

    localStorage.removeItem('produtosComprados');
    carregandoCarrinho();


    const snackbar = document.getElementById('snackbar');
    snackbar.className = 'snackbar show';
    setTimeout(() => {
        snackbar.className = snackbar.className.replace('show', '');
    }, 3000);
}
