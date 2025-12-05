package com.hourglass.game.api.service;
import java.util.Optional;

import com.hourglass.game.api.entity.UsuarioEntity;
import com.hourglass.game.api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;

    public UsuarioEntity incluir(UsuarioEntity Usuario) {
        return UsuarioRepository.save(Usuario);
    }

    public UsuarioEntity editar(int id, UsuarioEntity Usuario) {

        Optional<UsuarioEntity> UsuarioExistente =
                UsuarioRepository.findById(id);

        if (UsuarioExistente.isPresent()) {

            UsuarioEntity UsuarioAtualizado = UsuarioExistente.get();
            UsuarioAtualizado.setUsuario(Usuario.getUsuario());
            UsuarioAtualizado.setSenha(Usuario.getSenha());
            UsuarioAtualizado.setUpdatedAt(LocalDateTime.now());

            return UsuarioRepository.save(UsuarioAtualizado);
        } else {
            return null;
        }
    }

    public List<UsuarioEntity> listarTodos() {
        return UsuarioRepository.findAll();
    }

    public void excluir(Integer id) {
        UsuarioRepository.deleteById(id);
    }
}


