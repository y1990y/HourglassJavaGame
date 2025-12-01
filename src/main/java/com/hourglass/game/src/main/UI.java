package com.hourglass.game.src.main;

import com.hourglass.game.src.objeto.ObjetoChave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    PainelJogo pj;
    Font arial_40;
    BufferedImage imagemChave;
    public boolean mensagemAlerta = false;
    public String mensagem = "";
    int mensagemTempo = 0;

    public UI(PainelJogo pj) {
        this.pj = pj;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        ObjetoChave chave = new ObjetoChave();
        imagemChave = chave.imagem;
    }

    public void exibeMensagem(String text) {

        mensagem = text;
        mensagemAlerta = true;
    }

    public void draw(Graphics2D g2d) {
        // Não instancie classes aqui, tudo o que é instanciado aqui será instanciado 60x (FPS) por segundo

        g2d.setFont(arial_40);
        g2d.setColor(Color.white);
        g2d.drawImage(imagemChave, pj.tamanhoTile/2, pj.tamanhoTile/2, pj.tamanhoTile, pj.tamanhoTile, null);
        g2d.drawString("x "+ pj.player.temChave, 74, 65);

        //Mensagens
        if (mensagemAlerta == true) {

            g2d.setFont(g2d.getFont().deriveFont(25F));
            g2d.drawString(mensagem, pj.tamanhoTile/2, pj.tamanhoTile*5);

            mensagemTempo++;

            if (mensagemTempo > 180) {
                mensagemTempo = 0;
                mensagemAlerta = false;
            }
        }
    }
}
