package com.hourglass.game.src.sobreposicao;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SbrTree2 extends PrincipalSobr{

    public SbrTree2() {

        nome = "Arvore2";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/com/hourglass/game/assets/sobreposicao/tree_2.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
