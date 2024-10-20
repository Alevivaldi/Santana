package br.com.alexcosta.dto;

import br.com.alexcosta.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String imagem;
    private Double valor;


    public ProdutoDTO(Produto dto) {
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.imagem = dto.getImagem();
        this.valor = dto.getValor();

    }

}
