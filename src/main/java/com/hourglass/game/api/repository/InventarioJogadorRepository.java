package com.hourglass.game.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hourglass.game.api.entity.InventarioJogadorEntity;

@Repository
public interface InventarioJogadorRepository extends JpaRepository<InventarioJogadorEntity, Integer> {

    InventarioJogadorEntity findByJogadorIdAndItemId(int jogadorId, int itemId);
}