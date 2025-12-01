package com.hourglass.game.src.banco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hourglass.game.src.banco.conexao.Conexao;
import com.hourglass.game.src.banco.entidadesBD.Jogador;

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
        String sql = "INSERT INTO jogador (usuario_id, nome_jogador) VALUES (?, ?)";

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

    public void atualizarPosicao(int jogadorId, int x, int y) {
        String sql = "UPDATE jogador SET posicao_x = ?, posicao_y = ? WHERE usuario_id = ?";

        try (Connection conn = new Conexao().getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, x);
            stmt.setInt(2, y);
            stmt.setInt(3, jogadorId);

            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar posição do jogador: " + e.getMessage());
        }
    }
}
