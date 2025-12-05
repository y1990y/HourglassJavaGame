package com.hourglass.game.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.UsuarioEntity;
import com.hourglass.game.api.repository.UsuarioRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioEntity incluir(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity editar(int id, UsuarioEntity usuarioAtualizado) {
        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            UsuarioEntity usuarioDb = usuarioExistente.get();

            usuarioDb.setUsuario(usuarioAtualizado.getUsuario());
            usuarioDb.setSenha(usuarioAtualizado.getSenha());

            return usuarioRepository.save(usuarioDb);
        } else {
            return null;
        }
    }

    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void excluir(@NonNull Integer id) {
        usuarioRepository.deleteById(id);
    }
}