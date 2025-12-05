package com.hourglass.game.api.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class JogadorConquistaId implements Serializable {
    private int jogadorId;
    private int conquistaId;
}
