package com.hourglass.game.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inventario_jogador")
public class InventarioEntity {

    @Id
    @Column(name = "jogador_id")
    private Integer jogadorId;

    // Relacionamento One-to-One com JogadorEntity
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "jogador_id",
        referencedColumnName = "usuario_id",
        insertable = false,
        updatable = false
    )
    private JogadorEntity jogador;

    @Column(name = "qtd_chaves", nullable = false)
    private int qtdChaves = 0;

    @Column(name = "qtd_buffs_coletados", nullable = false)
    private int qtdBuffsColetados = 0;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    public void onCreate() {
        ultimaAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }
}
