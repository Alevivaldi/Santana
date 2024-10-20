package br.com.alexcosta.repository;

import br.com.alexcosta.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByUsername(String username);

}
