package com.hourglass.game.api.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class InventarioJogadorId implements Serializable {
    private int jogadorId;
    private int itemId;
}
