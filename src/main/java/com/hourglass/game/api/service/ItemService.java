package com.hourglass.game.api.service;

import com.hourglass.game.api.entity.ItemEntity;
import com.hourglass.game.api.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository ItemRepository;

    public ItemEntity incluir(ItemEntity Item) {
        return ItemRepository.save(Item);
    }

    public ItemEntity editar(int id, ItemEntity Item) {

        Optional<ItemEntity> ItemExistente =
                ItemRepository.findById(id);

        if (ItemExistente.isPresent()) {

            ItemEntity ItemAtualizado = ItemExistente.get();
            ItemAtualizado.setNomeItem(Item.getNomeItem());
            ItemAtualizado.setTipoItem(Item.getTipoItem());
            ItemAtualizado.setValorBase(Item.getValorBase());
            ItemAtualizado.setDescricao(Item.getDescricao());
            ItemAtualizado.setRaridade(Item.getRaridade());

            return ItemRepository.save(ItemAtualizado);
        } else {
            return null;
        }
    }

    public List<ItemEntity> listarTodos() {
        return ItemRepository.findAll();
    }

    public void excluir(Integer id) {
        ItemRepository.deleteById(id);
    }
}


