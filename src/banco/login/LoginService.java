package banco.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import banco.conexao.Conexao;

public class LoginService {

    public boolean validarLogin(String usuario, String senha) {
        String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND senha = ?";

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            return false;
        }
    }

    public Integer cadastrarUsuario(String usuario, String senha) {
        String sqlInsert = "INSERT INTO Usuarios (usuario, senha) OUTPUT INSERTED.id VALUES (?, ?)";

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();

            PreparedStatement inserir = conn.prepareStatement(sqlInsert);
            inserir.setString(1, usuario);
            inserir.setString(2, senha);

            ResultSet rs = inserir.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return null;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return null;
        }
    }


    public Integer obterUsuarioId(String usuario, String senha) {
        String sql = "SELECT id FROM usuarios WHERE usuario = ? AND senha = ?";

        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.getConexao();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id"); // retorna o ID do usuário
            }

            return null; // usuário/senha inválidos

        } catch (Exception e) {
            System.out.println("Erro ao buscar ID do usuário: " + e.getMessage());
            return null;
        }
    }

}
