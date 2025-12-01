package com.hourglass.game.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jogador")
public class JogadorEntity {

    @Id
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @Column(name = "nome_jogador", nullable = false, length = 100)
    private String nomeJogador;

    @Column(nullable = false)
    private int vida = 100;

    @Column(name = "posicao_x", nullable = false)
    private int posicaoX = 1152;

    @Column(name = "posicao_y", nullable = false)
    private int posicaoY = 960;

    @Column(name = "data_salvo")
    private LocalDateTime dataSalvo;

    @PrePersist
    public void onCreate() {
        dataSalvo = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        dataSalvo = LocalDateTime.now();
    }
}
