package com.hourglass.game.src.objeto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public final class ObjetoBau extends PrincipalObjeto{
    public ObjetoBau(){

        nome = "Baú";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/hourglass/game/assets/objetos/chest_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        colisao = true;
    }
}
