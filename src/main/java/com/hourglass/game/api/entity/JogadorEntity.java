package com.hourglass.game.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Jogadores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JogadorEntity {

    @Id
    @Column(name = "usuario_id")
    private int usuarioId;

    private String nomeJogador;

    private int vida = 100;

    private int posicaoX = 1152;

    private int posicaoY = 960;

    private LocalDateTime dataSalvo = LocalDateTime.now();

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
