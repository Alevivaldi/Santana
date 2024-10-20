package br.com.alexcosta.controllers;
import br.com.alexcosta.dto.CadastroDTO;
import br.com.alexcosta.dto.LoginRequest;
import br.com.alexcosta.entities.Cliente;
import br.com.alexcosta.entities.Endereco;
import br.com.alexcosta.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/clientes")
public class ClienteControllers {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping(value="/login")
    @Transactional(readOnly = false)
    public ResponseEntity<String> login(@RequestBody LoginRequest login) {
        if (login.getUsername() == null || login.getPassword() == null) {
            return ResponseEntity.badRequest().body("{\"message\": \"Usuário ou senha não podem ser nulos\"}");
        }

        Cliente user = clienteRepository.findByUsername(login.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Usuário não encontrado\"}");
        }

        if (!login.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Senha incorreta\"}");
        }

        return ResponseEntity.ok("{\"message\": \"Login realizado com sucesso\"}");
    }

    @PostMapping(value="/cadastrar")
    @Transactional
    public Cliente cadastrar(@RequestBody CadastroDTO cadastroDTO) {

        Cliente cliente = new Cliente();
        cliente.setNome(cadastroDTO.getNome());
        cliente.setUsername(cadastroDTO.getUsername());
        cliente.setPassword(cadastroDTO.getPassword());
        cliente.setDataNascimento(cadastroDTO.getDataNascimento());
        cliente.setCpf(cadastroDTO.getCpf());
        Endereco endereco = new Endereco();
        endereco.setCep(cadastroDTO.getEndereco().getCep());
        endereco.setLogradouro(cadastroDTO.getEndereco().getLogradouro());
        endereco.setNumero(cadastroDTO.getEndereco().getNumero());
        endereco.setBairro(cadastroDTO.getEndereco().getBairro());
        endereco.setCidade(cadastroDTO.getEndereco().getCidade());
        endereco.setEstado(cadastroDTO.getEndereco().getEstado());
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }
}

