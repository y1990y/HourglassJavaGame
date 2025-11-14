package sobreposicao;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class SbrTree4 extends PrincipalSobr{

    public SbrTree4() {

        nome = "Arvore4";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sobreposicao/tree_4.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
