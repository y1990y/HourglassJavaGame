package com.hourglass.game.api.entity;

import java.time.LocalDateTime;

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
@Table(name = "JogadorConquista")
@IdClass(JogadorConquistaId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JogadorConquistaEntity {

    @Id
    @Column(name = "usuario_id")
    private int jogadorId;

    @Id
    @Column(name = "conquista_id")
    private int conquistaId;

    private LocalDateTime dataConquista;

    @ManyToOne
    @JoinColumn(name = "jogador_id", insertable = false, updatable = false)
    private JogadorEntity jogador;

    @ManyToOne
    @JoinColumn(name = "conquista_id", insertable = false, updatable = false)
    private ConquistaItemEntity conquista;
}
