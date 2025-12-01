package com.hourglass.game.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hourglass.game.api.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    
    UsuarioEntity findByUsuarioAndSenha(String usuario, String senha);

    boolean existsByUsuario(String usuario);
}
