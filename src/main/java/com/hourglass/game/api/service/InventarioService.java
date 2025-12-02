package com.hourglass.game.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.InventarioEntity;
import com.hourglass.game.api.repository.InventarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    // Buscar inventário
    public InventarioEntity buscarPorJogadorId(int jogadorId) {
        return inventarioRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado para o jogador " + jogadorId));
    }

    // Criar novo inventário
    public InventarioEntity criar(int jogadorId, InventarioEntity body) {

        if (inventarioRepository.existsById(jogadorId)) {
            throw new RuntimeException("Inventário já existe para esse jogador");
        }

        InventarioEntity inv = new InventarioEntity();
        inv.setJogadorId(jogadorId);
        inv.setQtdChaves(body.getQtdChaves());
        inv.setQtdBuffsColetados(body.getQtdBuffsColetados());

        return inventarioRepository.save(inv);
    }

    // Atualizar inventário inteiro
    public InventarioEntity atualizar(int jogadorId, InventarioEntity body) {

        InventarioEntity inv = inventarioRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        inv.setQtdChaves(body.getQtdChaves());
        inv.setQtdBuffsColetados(body.getQtdBuffsColetados());

        return inventarioRepository.save(inv);
    }

    // Atualizar apenas quantidade de chaves
    public InventarioEntity atualizarChaves(int jogadorId, int qtd) {

        InventarioEntity inv = inventarioRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        inv.setQtdChaves(qtd);
        return inventarioRepository.save(inv);
    }

    // Atualizar apenas quantidade de buffs
    public InventarioEntity atualizarBuffs(int jogadorId, int qtd) {

        InventarioEntity inv = inventarioRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Inventário não encontrado"));

        inv.setQtdBuffsColetados(qtd);
        return inventarioRepository.save(inv);
    }

    // Excluir inventário
    public void excluir(int jogadorId) {
        if (!inventarioRepository.existsById(jogadorId)) {
            throw new RuntimeException("Inventário não existe");
        }
        inventarioRepository.deleteById(jogadorId);
    }

    // Listar todos os inventários
    public List<InventarioEntity> listarTodos() {
        return inventarioRepository.findAll();
    }
}
