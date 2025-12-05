package com.hourglass.game.api.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
public class InventarioJogadorId implements Serializable {

    private Integer jogadorId;
    private Integer itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventarioJogadorId)) return false;
        InventarioJogadorId that = (InventarioJogadorId) o;
        return Objects.equals(jogadorId, that.jogadorId) &&
               Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogadorId, itemId);
    }
}
