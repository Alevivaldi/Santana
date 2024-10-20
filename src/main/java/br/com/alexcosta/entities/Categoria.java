package br.com.alexcosta.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Entity
@Table(name="tb_categoria")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(mappedBy="categorias")
    private Set<Produto> produtos = new HashSet<>();

}
