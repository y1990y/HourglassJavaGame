package com.hourglass.game.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "vida")
    private int vida = 100;

    @Column(name = "posicao_x")
    private int posicaoX = 1152;

    @Column(name = "posicao_y")
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
