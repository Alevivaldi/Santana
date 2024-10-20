package br.com.alexcosta.dto;

import br.com.alexcosta.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter@NoArgsConstructor@AllArgsConstructor
public class CadastroDTO {
    private String nome;
    private String username;
    private String password;
    private LocalDate dataNascimento;
    private String cpf;
    private EnderecoDTO endereco;



    public CadastroDTO(String nome, String username, String password, LocalDate dataNascimento, EnderecoDTO endereco) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }
}
