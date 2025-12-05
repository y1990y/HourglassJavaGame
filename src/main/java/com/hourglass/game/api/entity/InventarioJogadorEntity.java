package com.hourglass.game.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "InventarioJogador")
@IdClass(InventarioJogadorId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventarioJogadorEntity {

    @Id
    @Column(name = "jogador_id")
    private int jogadorId;

    @Id
    @Column(name = "item_id")
    private int itemId;

    private int quantidadeAtual;
    private int totalColetado;

    @ManyToOne
    @JoinColumn(name = "jogador_id", insertable = false, updatable = false)
    private JogadorEntity jogador;

    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private ItemEntity item;
}


