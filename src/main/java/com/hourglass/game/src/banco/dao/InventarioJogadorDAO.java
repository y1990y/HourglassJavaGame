package com.hourglass.game.src.banco.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hourglass.game.src.banco.conexao.Conexao;
import com.hourglass.game.src.main.PainelJogo;

public class InventarioJogadorDAO {

    public void registrarItem(int jogadorId, int itemId, int quantidade) {

        String sql = "{ call sp_registrarItemJogador(?, ?, ?) }";

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            try (CallableStatement cs = con.prepareCall(sql)) {
                cs.setInt(1, jogadorId);
                cs.setInt(2, itemId);
                cs.setInt(3, quantidade);
                cs.execute();
            }

            con.close();

        } catch (SQLException e) {
            System.err.println("Erro ao registrar item (procedure): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro geral ao registrar item: " + e.getMessage());
        }
    }

    public int buscarQuantidadeAtual(int jogadorId, int itemId) {

        String sql = """
                    select quantidade_atual
                    from inventario_jogador
                    where jogador_id = ? and item_id = ?
                """;

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, jogadorId);
                ps.setInt(2, itemId);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("quantidade_atual");
                }
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao buscar quantidade atual: " + e.getMessage());
        }

        return 0;
    }

    public int buscarTotalColetado(int jogadorId, int itemId) {

        String sql = """
                    select total_coletado
                    from inventario_jogador
                    where jogador_id = ? and item_id = ?
                """;

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, jogadorId);
                ps.setInt(2, itemId);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("total_coletado");
                }
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao buscar total coletado: " + e.getMessage());
        }

        return 0;
    }

    public List<String> listarJogadoresComItemEpico() {

        List<String> resultado = new ArrayList<>();

        String sql = "select usuario_id, nome_jogador, total_coletado from v_jogadores_item_epico";

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int usuarioId = rs.getInt("usuario_id");
                    String nome = rs.getString("nome_jogador");
                    int total = rs.getInt("total_coletado");

                    resultado.add(
                            "id: " + usuarioId +
                                    " | jogador: " + nome +
                                    " | total épicos: " + total);
                }
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao consultar view v_jogadores_item_epico: " + e.getMessage());
        }

        return resultado;
    }

    public void verificarConquistasJogador(int jogadorId) {
        String sql = """
            select *
            from dbo.fc_progresso_conquistas_jogador(?)
        """;

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jogadorId);

            ResultSet rs = ps.executeQuery();

            System.out.println("==== PROGRESSO DAS CONQUISTAS ====");

            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;

                String nomeConquista = rs.getString("nome_conquista");
                double progresso = rs.getDouble("progresso_percentual");
                boolean concluida = rs.getInt("conquista_concluida") == 1;

                if (concluida) {
                    System.out.println("Conquista concluída: " + nomeConquista);
                } else {
                    System.out.println(
                        "Progresso em " + nomeConquista + ": "
                        + String.format("%.2f", progresso) + "%"
                    );
                }
            }

            if (!encontrou) {
                System.out.println("⚠ Nenhuma conquista encontrada para o jogador " + jogadorId);
            }

            System.out.println("=================================");

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao consultar conquistas: " + e.getMessage());
        }
    }


}
