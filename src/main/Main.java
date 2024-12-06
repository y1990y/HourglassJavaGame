package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame janela = new JFrame(); //Criação da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Permite que a janela feche de forma adequada quando o usuário aperta o botão "X" da janela
        janela.setResizable(false); //Definindo que a janela não possa ser redimensionada
        janela.setTitle("Teste Jogo 2D"); //Nome da janela do jogo

        PainelJogo painelJogo = new PainelJogo(); //Inicia o painel do jogo
        janela.add(painelJogo); //Adiciona o painel do jogo à janela

        janela.pack(); //Faz com que a janela se redimensione para os tamanhos de seus sub-componentes (main.PainelJogo)

        janela.setLocationRelativeTo(null); //Não especifica a posição da janela, assim ela será exibida no centro da tela
        janela.setVisible(true); //Faz com que seja possível ver a janela

        painelJogo.setupJogo();
        painelJogo.startThreadJogo();
    }
}