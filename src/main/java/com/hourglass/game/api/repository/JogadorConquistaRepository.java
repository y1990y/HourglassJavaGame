package com.hourglass.game.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hourglass.game.api.entity.JogadorConquistaEntity;

@Repository
public interface JogadorConquistaRepository extends JpaRepository<JogadorConquistaEntity, Integer> {
    JogadorConquistaEntity findByJogadorIdAndConquistaId(int jogadorId, int conquistaId);
}