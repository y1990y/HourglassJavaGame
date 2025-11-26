package banco.login;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

import banco.classesBD.Jogador;
import banco.dao.PlayerDAO;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    private final LoginService loginService;
    private Consumer<Jogador> callbackLogin;

    public TelaLogin(Consumer<Jogador> callbackLogin) {

        this.callbackLogin = callbackLogin;
        this.loginService = new LoginService();

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

        if (!loginService.validarLogin(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            return;
        }

        int usuarioId = loginService.obterUsuarioId(usuario, senha);
        if (usuarioId == -1) {
            JOptionPane.showMessageDialog(this, "Erro inesperado ao validar usuário.");
            return;
        }

        PlayerDAO playerDAO = new PlayerDAO();

        // Se o jogador não existir no BD = primeiro login
        if (!playerDAO.jogadorExiste(usuarioId)) {

            // Caixa de diálogo para nome do personagem
            String nomePersonagem = JOptionPane.showInputDialog(
                    this,
                    "Bem-vindo! Digite o nome do seu personagem:",
                    "Nome do Personagem",
                    JOptionPane.PLAIN_MESSAGE
            );

            // Cancelou ou vazio
            if (nomePersonagem == null || nomePersonagem.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Você precisa informar um nome para continuar!");
                return;
            }

            // Cria o jogador diretamente no banco com o nome inserido
            playerDAO.criarJogadorNovo(usuarioId, nomePersonagem.trim());
        }

        // Carrega o jogador do banco
        Jogador jogador = playerDAO.buscarJogadorPorId(usuarioId);
        if (jogador == null) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do jogador!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Bem-vindo, " + jogador.getNomePersonagem() + "!");

        dispose();

        // Passa o Jogador para o Main
        callbackLogin.accept(jogador);
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
            JOptionPane.showMessageDialog(this, "Usuário cadastrado! Agora realize o login para criar seu personagem.");
        } else {
            JOptionPane.showMessageDialog(this, "Este usuário já existe!");
        }
    }
}
