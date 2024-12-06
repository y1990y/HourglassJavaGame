package main;

import entidade.Player;
import objeto.PrincipalObjeto;
import tile.GerenciadorTile;

import javax.swing.*;
import java.awt.*;

public class PainelJogo extends JPanel implements Runnable {
    //O termo "extends" faz com que a classe herde todos os comportamentos da classe JPanel, sendo assim uma subclasse da classe JPanel
    //Implementar o Runnable à classe é o que permite o uso da Thread

    //Configurações da tela
    final int tamanhoOrgTile = 16; //O jogo estará utilizando um tile map de 16x16 px
    final int escala = 3; //Multiplicador da escala para exibição em maiores resoluções

    public final int tamanhoTile = tamanhoOrgTile * escala; //Cálculo real do tamanho dos elementos na tela, no caso atual 48x48 px
    public final int maxColTela = 16; //Número de colunas exibidas na tela
    public final int maxFilTela = 12; //Número de linhas exibidas na tela
    public final int larguraTela = tamanhoTile * maxColTela; //Define a largura da tela com base nos tamanhos anteriormente definidos (768 px)
    public final int alturaTela = tamanhoTile * maxFilTela; //Define a altura da tela com base nos tamanhos anteriormente definidos (576 px)

    // Configurações do Mundo

    public final int maxColMundo = 50;
    public final int maxFilMundo = 40;
    public final int larguraMundo = tamanhoTile * maxColMundo;
    public final int alturaMundo = tamanhoTile * maxFilMundo;

    // FPS
    int FPS = 60;
    public static int globalFPS;

    GerenciadorTile tileG = new GerenciadorTile(this);
    ControleTeclado conTec = new ControleTeclado();
    Thread threadJogo; //Pode ser iniciado e parado, mas fica ativo até que seja ordenado a parar
    public VerificaColi vColi = new VerificaColi(this);
    public DefinidorAsset aDef = new DefinidorAsset(this);
    public Player player = new Player(this,conTec);
    public PrincipalObjeto obj[] = new  PrincipalObjeto[10];

    public PainelJogo() {

        this.setPreferredSize(new Dimension(larguraTela, alturaTela)); //Define o tamanho dessa classe
        this.setBackground(Color.black); //Define a cor de fundo do painel para preto
        this.setDoubleBuffered(true); //Faz com que a renderização tenha um desempenho melhor
        this.addKeyListener(conTec); //Faz com que o programa consiga reconhecer os inputs de teclas
        this.setFocusable(true); //Permite que o painel do jogo possa ser focado para receber os inputs do teclado
    }

    public void setupJogo() {
        aDef.defineObjeto();
    }


    public void startThreadJogo() {
        threadJogo = new Thread(this); //Definindo a threadJogo e utilizando a classe main.PainelJogo (this)
        threadJogo.start(); //Iniciando a threadJogo (consequentemente o metodo run também)
    }
    @Override
    public void run() {

        double intervaloFrame = 1000000000 / FPS; // Intervalo entre a exibição entre frames
        double delta = 0; // Variável para verificação da exibição de frames
        long ultRegist = System.nanoTime(); // Ultimo registro de tempo
        long tempoAtual; // Variável usada para registro do tempo atual do programa em nanosegundos
        long timer = 0; // Variável usada para contagem de 1 segundo
        int contagemFrame = 0; // Variável usada para a contagem de quantos frames foram exibidos em 1 segundo

        while (threadJogo != null) {
            //Esse metodo será iniciado assim que a threadJogo for iniciada
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
                System.out.println("FPS: " + globalFPS); // Exibe quantos quadros por segundo estão acontecendo no programa pelo console
                contagemFrame = 0;
                timer = 0;
            }
        }
    }

    //Atualizações
    public void update() {

        player.update(); // Método "update" presente na classe "Player"

    }

    //Gráficos
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //A classe Graphics2D estende a classe Graphics fornecendo melhor controle de geometria, transformações de coordenadas, gerenciamento de cores e layout de texto.
        Graphics2D g2d = (Graphics2D) g;

        // Tile render
        tileG.render(g2d); // Método "render" presente na classe "GerenciadorTile"

        // Player render
        player.render(g2d); // Método "render" presente na classe "Player"
        // Object render
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) { // Evita o erro 'NullPointer'
                obj[i].render(g2d,this);
            }
        }

        g2d.dispose(); //Descarta as informações gráficas e libera quaisquer recursos do sistema que ele esteja usando, economizando memória

    }
}