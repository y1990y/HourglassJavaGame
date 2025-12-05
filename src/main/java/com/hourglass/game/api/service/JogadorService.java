package com.hourglass.game.api.service;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.repository.JogadorRepository;
import com.hourglass.game.api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final UsuarioRepository usuarioRepository;

    public JogadorEntity incluir(int usuarioId, String nomeJogador) {

        if (jogadorRepository.existsById(usuarioId)) {
            throw new RuntimeException("Jogador já existe");
        }

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        JogadorEntity jogador = new JogadorEntity();
        jogador.setUsuarioId(usuarioId);
        jogador.setNomeJogador(nomeJogador);

        return jogadorRepository.save(jogador);
    }

    public JogadorEntity buscarPorId(int usuarioId) {
        return jogadorRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado."));
    }

    public JogadorEntity atualizar(int usuarioId, JogadorEntity dados) {

        JogadorEntity jogador = jogadorRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        jogador.setVida(dados.getVida());
        jogador.setPosicaoX(dados.getPosicaoX());
        jogador.setPosicaoY(dados.getPosicaoY());

        return jogadorRepository.save(jogador);
    }

    public void excluir(int usuarioId) {

        if (!jogadorRepository.existsById(usuarioId)) {
            throw new RuntimeException("Jogador não existe.");
        }

        jogadorRepository.deleteById(usuarioId);
    }

    public List<JogadorEntity> listarTodos() {
        return jogadorRepository.findAll();
    }
}