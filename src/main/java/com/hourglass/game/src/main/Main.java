package com.hourglass.game.src.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.hourglass.game.GameApplication;
import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.service.JogadorService;
import com.hourglass.game.src.banco.login.LoginService;
import com.hourglass.game.src.banco.login.TelaLogin;

public class Main {

    public static void main(String[] args) {

        System.setProperty("java.awt.headless", "false");

        SpringApplication app = new SpringApplication(GameApplication.class);
        app.setHeadless(false);
        ApplicationContext context = app.run(args);

        LoginService loginService = context.getBean(LoginService.class);
        JogadorService jogadorService = context.getBean(JogadorService.class);

        SwingUtilities.invokeLater(() -> abrirTelaLogin(loginService, jogadorService));
    }

    private static void abrirTelaLogin(LoginService loginService, JogadorService jogadorService) {

        new TelaLogin(
                loginService,
                jogadorService,
                jogadorEntity -> iniciarJogo(jogadorEntity, jogadorService)
        );
    }

    private static void iniciarJogo(JogadorEntity jogadorEntity, JogadorService jogadorService) {

        PainelJogo painelJogo = new PainelJogo(jogadorEntity);

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

        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                try {
                    painelJogo.player.syncToEntity();

                    JogadorEntity atualizado = painelJogo.player.getJogadorEntity();

                    jogadorService.atualizar(atualizado.getUsuarioId(), atualizado);

                    // System.out.println("Posição e vida salva no banco:");
                    // System.out.println("X = " + atualizado.getPosicaoX());
                    // System.out.println("Y = " + atualizado.getPosicaoY());
                    // System.out.println("Vida = " + atualizado.getVida());

                } catch (Exception ex) {
                    System.out.println("Erro ao salvar dados do jogador: " + ex.getMessage());
                }
            }
        });
    }
}
