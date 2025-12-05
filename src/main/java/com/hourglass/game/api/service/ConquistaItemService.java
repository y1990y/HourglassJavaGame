package com.hourglass.game.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.ConquistaItemEntity;
import com.hourglass.game.api.repository.ConquistaItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConquistaItemService {

    private final ConquistaItemRepository ConquistaItemRepository;

    public ConquistaItemEntity incluir(ConquistaItemEntity Conquista) {
        return ConquistaItemRepository.save(Conquista);
    }

    public ConquistaItemEntity editar(int id, ConquistaItemEntity Conquista) {

        Optional<ConquistaItemEntity> ConquistaExistente =
                ConquistaItemRepository.findById(id);

        if (ConquistaExistente.isPresent()) {

            ConquistaItemEntity ConquistaAtualizada = ConquistaExistente.get();
            ConquistaAtualizada.setNomeConquista(Conquista.getNomeConquista());
            ConquistaAtualizada.setDescricao(Conquista.getDescricao());
            ConquistaAtualizada.setItem(Conquista.getItem());
            ConquistaAtualizada.setQtdNecessaria(Conquista.getQtdNecessaria());

            return ConquistaItemRepository.save(ConquistaAtualizada);
        } else {
            return null;
        }
    }

    public List<ConquistaItemEntity> listarTodos() {
        return ConquistaItemRepository.findAll();
    }

    public void excluir(Integer id) {
        ConquistaItemRepository.deleteById(id);
    }
}

