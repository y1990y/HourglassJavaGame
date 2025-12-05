package com.hourglass.game.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Itens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItem;

    private String nomeItem;

    private String tipoItem;

    private double valorBase;

    private String descricao;

    private String raridade;
}

