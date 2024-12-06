package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ChaveObjeto extends PrincipalObjeto{
    public ChaveObjeto(){

        nome = "Tecla";

        try {
            imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objetos/chest_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
