package objeto;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjetoPorta extends PrincipalObjeto{

        public ObjetoPorta(){

            nome = "Porta";

            try {
                imagem = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objetos/door_1.png")));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
