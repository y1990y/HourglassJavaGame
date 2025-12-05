package com.hourglass.game.api.service;

import com.hourglass.game.api.entity.InventarioJogadorEntity;
import com.hourglass.game.api.repository.InventarioJogadorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioJogadorService {

    private final InventarioJogadorRepository InventarioJogadorRepository;

    public InventarioJogadorEntity incluir(InventarioJogadorEntity Inventario) {
        return InventarioJogadorRepository.save(Inventario);
    }

    public InventarioJogadorEntity editar(
            int jogadorId, int itemId, InventarioJogadorEntity Inventario) {

        InventarioJogadorEntity InventarioExistente =
                InventarioJogadorRepository.findByJogadorIdAndItemId(jogadorId, itemId);

        if (InventarioExistente != null) {

            InventarioExistente.setQuantidadeAtual(Inventario.getQuantidadeAtual());
            InventarioExistente.setTotalColetado(Inventario.getTotalColetado());

            return InventarioJogadorRepository.save(InventarioExistente);
        } else {
            return null;
        }
    }

    public List<InventarioJogadorEntity> listarTodos() {
        return InventarioJogadorRepository.findAll();
    }

    public void excluir(int jogadorId, int itemId) {

        InventarioJogadorEntity Inventario =
                InventarioJogadorRepository.findByJogadorIdAndItemId(jogadorId, itemId);

        if (Inventario != null) {
            InventarioJogadorRepository.delete(Inventario);
        }
    }
}

