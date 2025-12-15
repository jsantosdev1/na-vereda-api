package com.jsantosdevjava.navereda.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String cep;
    private String numero;
    private String complemento;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private Double latitude;
    private Double longitude;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Preferencia preferencia;

    public User(String nome, String email, String senha, String cep, String numero, String complemento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }
}
