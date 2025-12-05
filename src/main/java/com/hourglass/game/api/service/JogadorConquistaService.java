package com.hourglass.game.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.JogadorConquistaEntity;
import com.hourglass.game.api.entity.JogadorConquistaId;
import com.hourglass.game.api.repository.JogadorConquistaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JogadorConquistaService {

    private final JogadorConquistaRepository jogadorConquistaRepository;

     public JogadorConquistaEntity incluir(JogadorConquistaEntity JC) {
        if (JC.getDataConquista() == null) {
            JC.setDataConquista(LocalDateTime.now());
        }
        return jogadorConquistaRepository.save(JC);
    }

    public JogadorConquistaEntity editar(int jogadorId, int conquistaId, JogadorConquistaEntity JC) {
        JogadorConquistaId id = new JogadorConquistaId(jogadorId, conquistaId);
        Optional<JogadorConquistaEntity> opt = jogadorConquistaRepository.findById(id);

        if (opt.isPresent()) {
            JogadorConquistaEntity existente = opt.get();
            if (JC.getDataConquista() != null) {
                existente.setDataConquista(JC.getDataConquista());
            }
            return jogadorConquistaRepository.save(existente);
        } else {
            return null;
        }
    }

    public List<JogadorConquistaEntity> listarTodos() {
        return jogadorConquistaRepository.findAll();
    }

    public void excluir(int jogadorId, int conquistaId) {
        JogadorConquistaId id = new JogadorConquistaId(jogadorId, conquistaId);
        if (jogadorConquistaRepository.existsById(id)) {
            jogadorConquistaRepository.deleteById(id);
        }
    }
}

