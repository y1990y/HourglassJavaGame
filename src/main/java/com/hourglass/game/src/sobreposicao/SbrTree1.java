package com.hourglass.game.src.sobreposicao;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SbrTree1 extends PrincipalSobr{

    public SbrTree1() {

        nome = "Arvore1";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/hourglass/game/assets/sobreposicao/tree_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
