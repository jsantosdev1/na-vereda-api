package com.jsantosdevjava.navereda.service;

import com.jsantosdevjava.navereda.model.User;
import com.jsantosdevjava.navereda.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private final UserRepository repositorio;

    public UserService(UserRepository repositorio) {
        this.repositorio = repositorio;
    }

    public User cadastrarUsuario(User usuario) {
        //regra de nogocio pra verificar se o user ja existe
        if (repositorio.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado na Vereda");
        }
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

    //DTO interno para pegar os dados do ViaCEP (Campos têm que ser iguais ao JSON da API)
    //usar static para não depender da instância do Service
    public static class ViaCepDto {
        public String cep;
        public String logradouro;
        public String bairro;
        public String localidade;
        public String uf;
    }
}
