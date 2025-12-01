package com.hourglass.game.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hourglass.game.api.entity.JogadorEntity;

@Repository
public interface JogadorRepository extends JpaRepository<JogadorEntity, Integer> {
}
