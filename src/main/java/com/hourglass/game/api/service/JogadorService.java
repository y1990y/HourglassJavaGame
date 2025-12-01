package com.hourglass.game.api.service;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.repository.JogadorRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    // Criar jogador
    public JogadorEntity incluir(int usuarioId, JogadorEntity body) {

        if (jogadorRepository.existsById(usuarioId)) {
            throw new RuntimeException("Jogador já existe");
        }

        JogadorEntity jogador = new JogadorEntity();
        jogador.setUsuarioId(usuarioId);
        jogador.setNomeJogador(body.getNomeJogador());
        jogador.setVida(100);
        jogador.setPosicaoX(1152);
        jogador.setPosicaoY(960);

        return jogadorRepository.save(jogador);
    }


    // Buscar jogador
    public JogadorEntity buscarPorId(int usuarioId) {
        return jogadorRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
    }

    // Atualizar jogador
    public JogadorEntity atualizar(int usuarioId, JogadorEntity body) {

        JogadorEntity jogador = jogadorRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        jogador.setNomeJogador(body.getNomeJogador());
        jogador.setVida(body.getVida());
        jogador.setPosicaoX(body.getPosicaoX());
        jogador.setPosicaoY(body.getPosicaoY());

        return jogadorRepository.save(jogador);
    }

    // Remover jogador
    public void excluir(int usuarioId) {
        if (!jogadorRepository.existsById(usuarioId)) {
            throw new RuntimeException("Jogador não existe");
        }
        jogadorRepository.deleteById(usuarioId);
    }

    // Listar todos os jogadores
    public List<JogadorEntity> listarTodos() {
        return jogadorRepository.findAll();
    }
}
