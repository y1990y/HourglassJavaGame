import javax.swing.*;
import java.awt.*;

public class PainelJogo extends JPanel implements Runnable {
    //O termo "extends" faz com que a classe herde todos os comportamentos da classe JPanel, sendo assim uma subclasse da classe JPanel
    //Implementar o Runnable à classe é o que permite o uso da Thread

    //Configurações da tela
    final int tamanhoOrgTile = 16; //O jogo estará utilizando um tile map de 16x16 px
    final int escala = 3; //Multiplicador da escala para exibição em maiores resoluções

    final int tamanhoTile = tamanhoOrgTile * escala; //Cálculo real do tamanho dos elementos na tela, no caso atual 48x48 px

    final int maxColTela = 16; //Número de colunas exibidas na tela
    final int maxLinTela = 12; //Número de linhas exibidas na tela

    final int larguraTela = tamanhoTile * maxColTela; //Define a largura da tela com base nos tamanhos anteriormente definidos (768 px)
    final int alturaTela = tamanhoTile * maxLinTela; //Define a altura da tela com base nos tamanhos anteriormente definidos (576 px)

    controleTeclado conTec = new controleTeclado();
    Thread threadJogo; //Pode ser iniciado e parado, mas fica ativo até que seja ordenado a parar

    //Definições iniciais do jogador
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public PainelJogo() {

        this.setPreferredSize(new Dimension(larguraTela, alturaTela)); //Define o tamanho dessa classe
        this.setBackground(Color.black); //Define a cor de fundo do painel para preto
        this.setDoubleBuffered(true); //Faz com que a renderização tenha um desempenho melhor
        this.addKeyListener(conTec); //Faz com que o programa consiga reconhecer os inputs de teclas
        this.setFocusable(true); //Permite que o painel do jogo possa ser focado para receber os inputs do teclado
    }


    public void startThreadJogo() {
        threadJogo = new Thread(this); //Definindo a threadJogo e utilizando a classe PainelJogo (this)
        threadJogo.start(); //Iniciando a threadJogo (consequentemente o metodo run também)
    }
    @Override
    public void run() {
        //Esse metodo será iniciado assim que a threadJogo for iniciada
        while (threadJogo != null) {

//          System.out.println("O loop está rodando");

            //Atualizações: Atualiza coisas como a posição do personagem
            update(); //Ativa esse metodo toda vez que o loop reinicia

            //Exibição: Exibe a tela com as informações atualizadas
            repaint(); //Ativa esse metodo toda vez que o loop reinicia
        }

    }

    //Atualizações
    public void update() {

        //Movimentação
        if (conTec.upPress == true) {
            playerY -= playerSpeed;
        }
        else if (conTec.downPress == true) {
            playerY += playerSpeed;
        }
        else if (conTec.leftPress == true) {
            playerX -= playerSpeed;
        }
        else if (conTec.rightPress == true) {
            playerX += playerSpeed;
        }

    }

    //Gráficos
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //A classe Graphics2D estende a classe Graphics fornecendo melhor controle de geometria, transformações de coordenadas, gerenciamento de cores e layout de texto.
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.blue); //Define a cor dos objetos exibidos

        g2d.fillRect(playerX, playerY, tamanhoTile, tamanhoTile);
        g2d.dispose(); //Descarta as informações gráficas e libera quaisquer recursos do sistema que ele esteja usando, economizando memória

    }
}