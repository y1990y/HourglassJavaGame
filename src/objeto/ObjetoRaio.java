package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public final class ObjetoRaio extends PrincipalObjeto{
    public ObjetoRaio(){

        nome = "Raio";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objetos/raio_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
