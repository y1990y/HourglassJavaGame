package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public final class ObjetoChave extends PrincipalObjeto{
    public ObjetoChave(){

        nome = "Chave";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objetos/key_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
