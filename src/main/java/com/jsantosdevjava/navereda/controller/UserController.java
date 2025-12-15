package com.jsantosdevjava.navereda.controller;

import com.jsantosdevjava.navereda.model.Preferencia;
import com.jsantosdevjava.navereda.model.User;
import com.jsantosdevjava.navereda.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService servico;

    public UserController(UserService servico) { //aqui faço a injeção de dependência via constr
        this.servico = servico;
    }

    @PostMapping
    public User criarUsuario(@RequestBody @Valid UsuarioDto dados) { //aqui é onde converto, pego o DTO (json) e transformo na entity (banco)
        User novoUsuario = new User(dados.nome(), dados.email(), dados.senha(), dados.cep(), dados.numero(), dados.complemento()
        );
        return servico.cadastrarUsuario(novoUsuario);
    }

    @PostMapping("/{id}/preferencias")
    public Preferencia adicionarPreferencia(@PathVariable UUID id, @RequestBody PreferenciaDto dados) {
        Preferencia novaPreferencia = new Preferencia(
                dados.orcamento(),
                dados.estilo(),
                dados.distancia()
        );
        return servico.salvarPreferencia(id, novaPreferencia);
    }

    public record UsuarioDto(
            @NotBlank(message = "O nome é obrigatório")
            @Size(min = 3, max = 100, message = "O nome deve ter pelo menos 3 caracteres")
            String nome,
            @NotBlank(message = "Um e-mail é obrigatório")
            @Email(message = "Formato de e-mail inserido é inválido")
            String email,
            @NotBlank
            @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
            String senha,
            @NotBlank(message = "O CEP é obrigatório")
            String cep,
            String numero,
            String complemento) { }
    //record pra receber o DTO
    public record PreferenciaDto(BigDecimal orcamento, com.jsantosdevjava.navereda.model.EstiloViagem estilo, Integer distancia
    ) {}
}