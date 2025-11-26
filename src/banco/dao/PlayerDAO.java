package banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import banco.classesBD.Jogador;
import banco.conexao.Conexao;

public class PlayerDAO {

    // Verifica se já existe jogador cadastrado para o usuário
    public boolean jogadorExiste(int usuarioId) {
        String sql = "SELECT 1 FROM jogador WHERE usuario_id = ?";

        try (Connection conn = new Conexao().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // se encontrou, existe

        } catch (Exception e) {
            System.out.println("Erro ao verificar jogador: " + e.getMessage());
            return false;
        }
    }

    // Cria um jogador novo com o nome informado
    public void criarJogadorNovo(int usuarioId, String nomePersonagem) {
        String sql = "INSERT INTO jogador (usuario_id, nome_jogador, vida, posicao_x, posicao_y) VALUES (?, ?, 100, 24, 20)";

        try (Connection conn = new Conexao().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            stmt.setString(2, nomePersonagem);
            stmt.executeUpdate();
            System.out.println("Jogador criado no banco com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar jogador: " + e.getMessage());
        }
    }

    // Carrega todos os dados do jogador como ResultSet
    public ResultSet carregarDadosJogador(int usuarioId) throws Exception {
        String sql = "SELECT * FROM jogador WHERE usuario_id = ?";
        Connection conn = new Conexao().getConexao();

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, usuarioId);

        return stmt.executeQuery();
    }

    // Busca e retorna um objeto Jogador completo
    public Jogador buscarJogadorPorId(int usuarioId) {
        String sql = "SELECT * FROM jogador WHERE usuario_id = ?";

        try (Connection conn = new Conexao().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Jogador jogador = new Jogador();
                jogador.setId(usuarioId);
                jogador.setNomePersonagem(rs.getString("nome_jogador"));
                jogador.setVida(rs.getInt("vida"));
                jogador.setPosicaoX(rs.getInt("posicao_x"));
                jogador.setPosicaoY(rs.getInt("posicao_y"));
                return jogador;
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar jogador: " + e.getMessage());
        }

        return null;
    }
}
