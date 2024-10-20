package br.com.alexcosta.controllers;

import br.com.alexcosta.dto.ProdutoDTO;
import br.com.alexcosta.entities.Produto;
import br.com.alexcosta.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoControllers {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/listar")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = produtos.stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtoDTOs);
    }

    @GetMapping("/procurarCategoria")
    @Transactional(readOnly = true)
    public ResponseEntity<List<ProdutoDTO>> getProdutos(
            @RequestParam(value = "categoriaId", required = false) Long categoriaId) {

        List<Produto> produtos = (categoriaId != null) ?
                produtoRepository.findByCategoriasId(categoriaId) :
                produtoRepository.findAll();

        List<ProdutoDTO> produtoDTOs = produtos.stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtoDTOs);
    }
}
