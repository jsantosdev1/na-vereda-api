package com.jsantosdevjava.navereda.controller;

import com.jsantosdevjava.navereda.model.User;
import com.jsantosdevjava.navereda.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService servico;

    public UserController(UserService servico) { //aqui faço a injeção de dependência via constr
        this.servico = servico;
    }

    @PostMapping
    public User criarUsuario(@RequestBody UsuarioDto dados) { //aqui é onde converto, pego o DTO (json) e transformo na entity (banco)
        User novoUsuario = new User(dados.nome(), dados.email(), dados.senha(), dados.cep(), dados.numero(), dados.complemento()
        );

        return servico.cadastrarUsuario(novoUsuario);
    }
    public record UsuarioDto(String nome, String email, String senha, String cep, String numero, String complemento) { }
    //record pra receber o DTO
}
