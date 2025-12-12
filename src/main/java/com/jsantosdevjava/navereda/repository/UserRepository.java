package com.jsantosdevjava.navereda.repository;

import com.jsantosdevjava.navereda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    //para o spring montar o sql sozinho baseado em (SELECT count(*) > 0 FROM tb_usuarios WHERE email = ?)
    boolean existsByEmail(String email);

}
