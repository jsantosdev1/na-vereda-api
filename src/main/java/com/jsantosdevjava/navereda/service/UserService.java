package com.jsantosdevjava.navereda.service;

import com.jsantosdevjava.navereda.model.Preferencia;
import com.jsantosdevjava.navereda.model.User;
import com.jsantosdevjava.navereda.repository.PreferenciaRepository;
import com.jsantosdevjava.navereda.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repositorio;
    private final PreferenciaRepository preferenciaRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repositorio, PreferenciaRepository preferenciaRepository, PasswordEncoder passwordEncoder) {
        this.repositorio = repositorio;
        this.preferenciaRepository = preferenciaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User cadastrarUsuario(User usuario) {
        //regra de nogocio pra verificar se o user ja existe
        if (repositorio.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado na Vereda");
        }
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        //integração: Buscar o CEP na API externa
        String cepFormatado = usuario.getCep();
        String url = "https://viacep.com.br/ws/" + cepFormatado + "/json/";

        //instanciamos direto aqui para simplificar (sem config extra)
        RestTemplate restTemplate = new RestTemplate();

        try {
            //buscar JSON e jogar na classe auxiliar (DTO)
            ViaCepDto enderecoEncontrado = restTemplate.getForObject(url, ViaCepDto.class);

            if (enderecoEncontrado != null && enderecoEncontrado.cep != null) {//preencher os dados do usuário com o que voltou da API
                usuario.setRua(enderecoEncontrado.logradouro);usuario.setBairro(enderecoEncontrado.bairro);
                usuario.setCidade(enderecoEncontrado.localidade);usuario.setEstado(enderecoEncontrado.uf);
            }
        } catch (Exception erro) {
            //tratamento pra caso der erro no ViaCEP
            System.out.println("Erro ao buscar CEP: " + erro.getMessage());
        }

        //salvar no banco de dados
        return repositorio.save(usuario);
    }

    public Preferencia salvarPreferencia(UUID idUsuario, Preferencia novaPreferencia) {
        User usuario = repositorio.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Preferencia existente = preferenciaRepository.findByUsuario(usuario);

        if (existente != null) {
            existente.setOrcamento(novaPreferencia.getOrcamento());
            existente.setEstilo(novaPreferencia.getEstilo());
            existente.setDistancia(novaPreferencia.getDistancia());
            return preferenciaRepository.save(existente);
        } else {
            novaPreferencia.setUsuario(usuario);
            return preferenciaRepository.save(novaPreferencia);
        }
    }

    public static class ViaCepDto {
        public String cep;
        public String logradouro;
        public String bairro;
        public String localidade;
        public String uf;
    }
}