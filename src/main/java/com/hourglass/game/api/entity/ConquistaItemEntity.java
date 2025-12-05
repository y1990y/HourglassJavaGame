package com.hourglass.game.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ConquistasItens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConquistaItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConquista;

    private String nomeConquista;

    private String descricao;

    private int qtdNecessaria;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;
}

