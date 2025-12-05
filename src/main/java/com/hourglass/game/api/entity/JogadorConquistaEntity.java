package com.hourglass.game.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
    @Column(name = "jogador_id")
    private int jogadorId;

    @Id
    @Column(name = "conquista_id")
    private int conquistaId;

    @Column(name = "data_conquista", nullable = false, columnDefinition = "datetime default SYSDATETIME()")
    private LocalDateTime dataConquista;

    @ManyToOne
    @MapsId("jogadorId")
    @JoinColumn(name = "jogador_id")
    private JogadorEntity jogador;

    @ManyToOne
    @MapsId("conquistaId")
    @JoinColumn(name = "conquista_id")
    private ConquistaItemEntity conquista;
}
