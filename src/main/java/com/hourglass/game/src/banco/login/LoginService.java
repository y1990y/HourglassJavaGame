package com.hourglass.game.src.banco.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.UsuarioEntity;
import com.hourglass.game.api.repository.UsuarioRepository;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean validarCredenciais(String usuario, String senha) {
        UsuarioEntity user = usuarioRepository.findByUsuarioAndSenha(usuario, senha);
        return user != null;
    }

    public Integer obterUsuarioId(String usuario, String senha) {
        UsuarioEntity user = usuarioRepository.findByUsuarioAndSenha(usuario, senha);
        return (user != null) ? user.getId() : null;
    }

    public Integer cadastrarUsuario(String usuario, String senha) {

        if (usuarioRepository.existsByUsuario(usuario)) {
            return null;
        }

        UsuarioEntity novo = new UsuarioEntity();
        novo.setUsuario(usuario);
        novo.setSenha(senha);

        UsuarioEntity salvo = usuarioRepository.save(novo);
        return salvo.getId();
    }
}
