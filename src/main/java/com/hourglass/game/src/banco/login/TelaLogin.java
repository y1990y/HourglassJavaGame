package com.hourglass.game.src.banco.login;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

import com.hourglass.game.api.entity.JogadorEntity;
import com.hourglass.game.api.service.JogadorService;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    private final LoginService loginService;
    private final JogadorService jogadorService;
    private final Consumer<JogadorEntity> callbackLogin;

    public TelaLogin(LoginService loginService,
                     JogadorService jogadorService,
                     Consumer<JogadorEntity> callbackLogin) {

        this.loginService = loginService;
        this.jogadorService = jogadorService;
        this.callbackLogin = callbackLogin;

        setTitle("Login - Hourglass");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Usuário:"));
        campoUsuario = new JTextField();
        add(campoUsuario);

        add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        add(campoSenha);

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.addActionListener(e -> realizarLogin());
        add(botaoLogin);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(e -> realizarCadastro());
        add(botaoCadastrar);

        setVisible(true);
    }

    private void realizarLogin() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (!loginService.validarCredenciais(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            return;
        }

        Integer usuarioId = loginService.obterUsuarioId(usuario, senha);

        if (usuarioId == null) {
            JOptionPane.showMessageDialog(this, "Erro inesperado ao carregar usuário.");
            return;
        }

        JogadorEntity jogadorEntity;

        try {
            jogadorEntity = jogadorService.buscarPorId(usuarioId);
        } catch (Exception e) {
            jogadorEntity = null;
        }

        if (jogadorEntity == null) {

            String nomePersonagem = JOptionPane.showInputDialog(
                    this,
                    "Bem-vindo! Digite o nome do seu personagem:",
                    "Criar Personagem",
                    JOptionPane.PLAIN_MESSAGE
            );

            if (nomePersonagem == null || nomePersonagem.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "É necessário informar um nome!");
                return;
            }

            jogadorEntity = jogadorService.incluir(usuarioId, nomePersonagem.trim());
        }

        JOptionPane.showMessageDialog(this, "Bem-vindo, " + jogadorEntity.getNomeJogador() + "!");

        dispose();
        callbackLogin.accept(jogadorEntity);
    }

    private void realizarCadastro() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha usuário e senha para cadastrar!");
            return;
        }

        Integer novoId = loginService.cadastrarUsuario(usuario, senha);

        if (novoId != null) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado! Agora realize o login.");
        } else {
            JOptionPane.showMessageDialog(this, "Este usuário já existe!");
        }
    }
}
