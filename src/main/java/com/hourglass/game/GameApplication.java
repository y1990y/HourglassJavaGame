package com.hourglass.game;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.service.JogadorService;
import com.hourglass.game.src.banco.login.LoginService;
import com.hourglass.game.src.banco.login.TelaLogin;
import com.hourglass.game.src.main.PainelJogo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SpringBootApplication
public class GameApplication {

    public static void main(String[] args) {

        System.setProperty("java.awt.headless", "false");

        SpringApplication app = new SpringApplication(GameApplication.class);

        app.setHeadless(false);

        app.run(args);
    }

    @Bean
    public ApplicationRunner iniciarJogoComInterface(
            LoginService loginService,
            JogadorService jogadorService
    ) {
        return args -> {

            SwingUtilities.invokeLater(() -> {

                new TelaLogin(
                        loginService,
                        jogadorService,
                        jogadorEntity ->
                                iniciarFluxoDoJogo(jogadorEntity, jogadorService)
                );
            });
        };
    }

    private void iniciarFluxoDoJogo(JogadorEntity jogadorEntity, JogadorService jogadorService) {

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

                    System.out.println("Posição salva ao sair!");
                } catch (Exception ex) {
                    System.out.println("Erro ao salvar dados do jogador: " + ex.getMessage());
                }
            }
        });
    }
}
