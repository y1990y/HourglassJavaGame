package com.hourglass.game.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.entity.UsuarioEntity;
import com.hourglass.game.api.repository.JogadorRepository;
import com.hourglass.game.api.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final UsuarioRepository usuarioRepository;

    // ✅ Equivalente ao jogadorExiste(int usuarioId)
    public boolean jogadorExiste(Integer usuarioId) {
        return jogadorRepository.existsByUsuarioId(usuarioId);
    }

    // ✅ Equivalente ao criarJogadorNovo(int usuarioId, String nomePersonagem)
    public JogadorEntity criarJogadorNovo(Integer usuarioId, String nomePersonagem) {

        UsuarioEntity usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        JogadorEntity jogador = new JogadorEntity();
        jogador.setUsuario(usuario);
        jogador.setUsuarioId(usuarioId);
        jogador.setNomeJogador(nomePersonagem);
        jogador.setVida(100);
        jogador.setPosicaoX(1152);
        jogador.setPosicaoY(960);

        return jogadorRepository.save(jogador);
    }

    // ✅ Equivalente ao buscarJogadorPorId(int usuarioId)
    public JogadorEntity buscarPorUsuarioId(Integer usuarioId) {
        return jogadorRepository.findByUsuarioId(usuarioId)
            .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
    }

    // ✅ Equivalente ao atualizarPosicao(int jogadorId, int x, int y)
    public JogadorEntity atualizarPosicao(Integer usuarioId, int x, int y) {

        JogadorEntity jogador = buscarPorUsuarioId(usuarioId);

        jogador.setPosicaoX(x);
        jogador.setPosicaoY(y);

        return jogadorRepository.save(jogador);
    }

    // ✅ Já estava certo no seu código
    public JogadorEntity incluir(JogadorEntity jogador) {
        return jogadorRepository.save(jogador);
    }

    public JogadorEntity editar(int id, JogadorEntity jogador) {

        return jogadorRepository.findById(id)
            .map(jogadorExistente -> {
                jogadorExistente.setNomeJogador(jogador.getNomeJogador());
                jogadorExistente.setVida(jogador.getVida());
                jogadorExistente.setPosicaoX(jogador.getPosicaoX());
                jogadorExistente.setPosicaoY(jogador.getPosicaoY());
                jogadorExistente.setDataSalvo(jogador.getDataSalvo());
                return jogadorRepository.save(jogadorExistente);
            }).orElse(null);
    }

    public List<JogadorEntity> listarTodos() {
        return jogadorRepository.findAll();
    }

    public void excluir(Integer id) {
        jogadorRepository.deleteById(id);
    }
}
