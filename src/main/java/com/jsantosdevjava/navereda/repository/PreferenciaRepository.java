package com.jsantosdevjava.navereda.repository;

import com.jsantosdevjava.navereda.model.Preferencia;
import com.jsantosdevjava.navereda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PreferenciaRepository extends JpaRepository<Preferencia, UUID> {

    Preferencia findByUsuario(User usuario);
}