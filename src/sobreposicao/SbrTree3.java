package sobreposicao;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SbrTree3 extends PrincipalSobr{

    public SbrTree3() {

        nome = "Arvore3";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sobreposicao/tree_3.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
