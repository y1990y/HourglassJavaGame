package com.hourglass.game.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jogador")
public class JogadorEntity {

    @Id
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "nome_jogador", nullable = false)
    private String nomeJogador;

    @Column(name = "vida")
    private int vida = 100;

    @Column(name = "posicao_x")
    private int posicaoX = 1152;

    @Column(name = "posicao_y")
    private int posicaoY = 960;

    @Column(name = "data_salvo")
    private LocalDateTime dataSalvo;

    @PrePersist
    public void prePersist() {
        if (dataSalvo == null)
            dataSalvo = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataSalvo = LocalDateTime.now();
    }
}