package com.hourglass.game.src.main;

import com.hourglass.game.src.objeto.PrincipalObjeto;
import com.hourglass.game.src.sobreposicao.PrincipalSobr;
import com.hourglass.game.src.tile.GerenciadorTile;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.src.entidadesJogo.Player;

import javax.swing.*;
import java.awt.*;

public class PainelJogo extends JPanel implements Runnable {

    // Configurações da tela
    final int tamanhoOrgTile = 16;
    final int escala = 3;

    public final int tamanhoTile = tamanhoOrgTile * escala;
    public final int maxColTela = 16;
    public final int maxFilTela = 12;
    public final int larguraTela = tamanhoTile * maxColTela;
    public final int alturaTela = tamanhoTile * maxFilTela;

    // Configurações do mundo
    public final int maxColMundo = 50;
    public final int maxFilMundo = 40;

    // FPS
    int FPS = 60;
    public static int globalFPS;

    // Sistema
    GerenciadorTile tileG = new GerenciadorTile(this);
    ControleTeclado conTec = new ControleTeclado();
    Thread threadJogo;
    public VerificaColi vColi = new VerificaColi(this);
    public DefinidorAsset aDef = new DefinidorAsset(this);
    Som musica = new Som();
    Som som = new Som();
    public UI ui = new UI(this);

    // Entidades
    public Player player;
    public PrincipalObjeto obj[] = new PrincipalObjeto[15];
    public PrincipalSobr sbr[] = new PrincipalSobr[20];

    private JogadorEntity jogadorEntity;

    public PainelJogo(JogadorEntity jogadorEntity) {

        this.jogadorEntity = jogadorEntity;

        this.setPreferredSize(new Dimension(larguraTela, alturaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(conTec);
        this.setFocusable(true);

        this.player = new Player(this, conTec, jogadorEntity);
    }

    public void setupJogo() {
        aDef.defineObjeto();
        aDef.defineSbr();
        tocaMusica(0);
    }

    public void startThreadJogo() {
        threadJogo = new Thread(this);
        threadJogo.start();
    }

    @Override
    public void run() {

        double intervaloFrame = 1000000000 / FPS;
        double delta = 0;
        long ultRegist = System.nanoTime();
        long tempoAtual;
        long timer = 0;
        int contagemFrame = 0;

        while (threadJogo != null) {

            tempoAtual = System.nanoTime();
            delta += (tempoAtual - ultRegist) / intervaloFrame;
            timer += (tempoAtual - ultRegist);
            ultRegist = tempoAtual;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                contagemFrame++;
            }

            if (timer >= 1000000000) {
                globalFPS = contagemFrame;
                contagemFrame = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileG.render(g2d);

        for (PrincipalObjeto o : obj) {
            if (o != null) o.render(g2d, this);
        }

        player.render(g2d);

        for (PrincipalSobr s : sbr) {
            if (s != null) s.render(g2d, this);
        }

        ui.draw(g2d);

        g2d.dispose();
    }

    public JogadorEntity getJogadorEntity() {
        return jogadorEntity;
    }

    public void tocaMusica(int i) {
        musica.defineArquivo(i);
        musica.playMusica();
        musica.loop();
    }

    public void paraMusica() {
        musica.stop();
    }

    public void tocaSFX(int i) {
        som.defineArquivo(i);
        som.playSFX();
    }
}
