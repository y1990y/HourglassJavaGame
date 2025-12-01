package com.hourglass.game.src.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.hourglass.game.GameApplication;
import com.hourglass.game.src.banco.dao.PlayerDAO;
import com.hourglass.game.src.banco.entidadesBD.Jogador;
import com.hourglass.game.src.banco.login.TelaLogin;
import com.hourglass.game.src.banco.login.LoginService;

public class Main {

    public static void main(String[] args) {

        // Necessário para o Swing funcionar com Spring Boot
        System.setProperty("java.awt.headless", "false");

        // Inicia o Spring Boot SEM modo headless
        SpringApplication app = new SpringApplication(GameApplication.class);
        app.setHeadless(false);
        ApplicationContext context = app.run(args);

        // Obtém LoginService do Spring
        LoginService loginService = context.getBean(LoginService.class);

        // Inicia a tela de login na thread correta do Swing
        SwingUtilities.invokeLater(() -> abrirTelaLogin(loginService));
    }

    private static void abrirTelaLogin(LoginService loginService) {

        new TelaLogin(loginService, jogador -> {
            // Fez login com sucesso → iniciar jogo!
            iniciarJogo(jogador);
        });
    }

    private static void iniciarJogo(Jogador jogador) {

        PainelJogo painelJogo = new PainelJogo(jogador);

        JFrame janela = new JFrame();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("Hourglass - Jogo");

        janela.add(painelJogo);
        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        painelJogo.setupJogo();
        painelJogo.startThreadJogo();

        // Salvar posição ao fechar
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                PlayerDAO dao = new PlayerDAO();
                dao.atualizarPosicao(
                        painelJogo.player.getJogadorId(),
                        painelJogo.player.mundoX,
                        painelJogo.player.mundoY
                );

                System.out.println("Posição salva ao sair!");
            }
        });
    }
}
