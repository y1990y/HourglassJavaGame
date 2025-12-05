package com.hourglass.game.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hourglass.game.api.entity.InventarioJogadorId;
import com.hourglass.game.api.entity.JogadorConquistaEntity;
import com.hourglass.game.api.entity.JogadorConquistaId;

@Repository
public interface JogadorConquistaRepository extends JpaRepository<JogadorConquistaEntity, JogadorConquistaId> {
    JogadorConquistaEntity findByJogadorIdAndConquistaId(int jogadorId, int conquistaId);
}