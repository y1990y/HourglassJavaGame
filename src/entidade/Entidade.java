package entidade;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entidade {
    // Essa classe armazena todas as variáveis de entidades presentes no jogo

    public int mundoX, mundoY;
    public int speed, trocaSpriteVel;

    public BufferedImage
            down_1, down_2, down_3, down_4, down_5, down_6, down_7, down_8,
            right_1, right_2, right_3, right_4, right_5, right_6, right_7, right_8,
            left_1, left_2, left_3, left_4, left_5, left_6, left_7, left_8,
            up_1, up_2, up_3, up_4, up_5, up_6, up_7, up_8; // Descreve uma imagem a partir de um buffer acessível de dados de imagem
    public String direcao;

    public int contaSprite = 0;
    public int numeroSprite = 1;

    public Rectangle areaSolida;
    public boolean colisaoAtiva = false;
}
