package br.com.alexcosta.repository;

import br.com.alexcosta.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    List<Produto> findByCategoriasId(Long categoriaId);
}
