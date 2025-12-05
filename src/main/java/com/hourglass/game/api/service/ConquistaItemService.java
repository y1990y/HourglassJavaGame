package com.hourglass.game.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hourglass.game.api.entity.ConquistaItemEntity;
import com.hourglass.game.api.entity.ItemEntity;
import com.hourglass.game.api.repository.ConquistaItemRepository;
import com.hourglass.game.api.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConquistaItemService {

    private final ConquistaItemRepository ConquistaItemRepository;
    private final ItemRepository itemRepository;

    public ConquistaItemEntity incluir(int itemId, ConquistaItemEntity conquista) {

        // ✅ REGRA: não permite duplicar a mesma conquista (se existir essa validação no projeto)
        if (conquista.getIdConquista() != 0 &&
            ConquistaItemRepository.existsById(conquista.getIdConquista())) {
            throw new RuntimeException("Conquista já existe");
        }

        // ✅ BUSCA O ITEM OBRIGATORIAMENTE
        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        // ✅ ASSOCIA O ITEM GERENCIADO PELO HIBERNATE
        conquista.setItemId(item);

        return ConquistaItemRepository.save(conquista);
    }

    public ConquistaItemEntity editar(int id, ConquistaItemEntity Conquista) {

        Optional<ConquistaItemEntity> ConquistaExistente =
                ConquistaItemRepository.findById(id);

        if (ConquistaExistente.isPresent()) {

            ConquistaItemEntity ConquistaAtualizada = ConquistaExistente.get();
            ConquistaAtualizada.setNomeConquista(Conquista.getNomeConquista());
            ConquistaAtualizada.setDescricao(Conquista.getDescricao());
            ConquistaAtualizada.setItemId(Conquista.getItemId());
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

