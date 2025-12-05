package com.hourglass.game.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.JogadorConquistaEntity;
import com.hourglass.game.api.repository.JogadorConquistaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JogadorConquistaService {

    private final JogadorConquistaRepository JogadorConquistaRepository;

    public JogadorConquistaEntity incluir(JogadorConquistaEntity JC) {
        return JogadorConquistaRepository.save(JC);
    }

    public JogadorConquistaEntity editar(
            int jogadorId, int conquistaId, JogadorConquistaEntity JC) {

        JogadorConquistaEntity JCExistente =
                JogadorConquistaRepository.findByJogadorIdAndConquistaId(jogadorId, conquistaId);

        if (JCExistente != null) {

            JCExistente.setDataConquista(JC.getDataConquista());

            return JogadorConquistaRepository.save(JCExistente);
        } else {
            return null;
        }
    }

    public List<JogadorConquistaEntity> listarTodos() {
        return JogadorConquistaRepository.findAll();
    }

    public void excluir(int jogadorId, int conquistaId) {

        JogadorConquistaEntity JC =
                JogadorConquistaRepository.findByJogadorIdAndConquistaId(jogadorId, conquistaId);

        if (JC != null) {
            JogadorConquistaRepository.delete(JC);
        }
    }
}

