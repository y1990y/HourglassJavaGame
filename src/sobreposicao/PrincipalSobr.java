package sobreposicao;

import main.PainelJogo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PrincipalSobr {

    public BufferedImage imagem;
    public String nome;
    public boolean colisao = false;
    public int mundoX, mundoY;

    public void render(Graphics2D g2d, PainelJogo pj) {

        int telaX = mundoX - pj.player.mundoX + pj.player.telaX; // Define a posição horizontal dos objetos com relação à tela do jogo
        int telaY = mundoY - pj.player.mundoY + pj.player.telaY; // Define a posição vertical dos objetos com relação à tela do jogo

        if     (mundoX + pj.tamanhoTile > pj.player.mundoX - pj.player.telaX &&
                mundoX - pj.tamanhoTile < pj.player.mundoX + pj.player.telaX &&
                mundoY + pj.tamanhoTile > pj.player.mundoY - pj.player.telaY &&
                mundoY - pj.tamanhoTile < pj.player.mundoY + pj.player.telaY) {

            g2d.drawImage(imagem, telaX, telaY, pj.tamanhoTile, pj.tamanhoTile, null); // Faz com que as tiles apareçam
        }
    }
}
