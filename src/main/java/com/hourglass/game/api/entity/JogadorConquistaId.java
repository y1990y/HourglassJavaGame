package com.hourglass.game.api.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogadorConquistaId implements Serializable {

    private Integer jogadorId;
    private Integer conquistaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JogadorConquistaId)) return false;
        JogadorConquistaId that = (JogadorConquistaId) o;
        return Objects.equals(jogadorId, that.jogadorId) &&
               Objects.equals(conquistaId, that.conquistaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jogadorId, conquistaId);
    }
}
