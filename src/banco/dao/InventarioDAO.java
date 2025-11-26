package banco.dao;

import banco.classesBD.Inventario;
import banco.conexao.Conexao;

import java.sql.*;
import java.time.LocalDateTime;

public class InventarioDAO {

    /**
     * Atualiza ou insere o inventário do jogador no banco.
     * Se já existir um registro, ele é atualizado. Caso contrário, é criado.
     */
    public void salvarOuAtualizarInventario(Inventario inventario) {
        String sql = """
            MERGE INTO inventario_jogador AS destino
            USING (SELECT ? AS jogador_id, ? AS qtd_chaves, ? AS qtd_buffs_coletados) AS origem
            ON destino.jogador_id = origem.jogador_id
            WHEN MATCHED THEN
                UPDATE SET 
                    qtd_chaves = origem.qtd_chaves,
                    qtd_buffs_coletados = origem.qtd_buffs_coletados,
                    ultima_atualizacao = SYSDATETIME()
            WHEN NOT MATCHED THEN
                INSERT (jogador_id, qtd_chaves, qtd_buffs_coletados, ultima_atualizacao)
                VALUES (origem.jogador_id, origem.qtd_chaves, origem.qtd_buffs_coletados, SYSDATETIME());
        """;

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, inventario.getJogadorId());
                ps.setInt(2, inventario.getQtdChaves());
                ps.setInt(3, inventario.getQtdBuffsColetados());
                ps.executeUpdate();
                System.out.println("Inventário atualizado com sucesso para o jogador ID " + inventario.getJogadorId());
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao atualizar inventário: " + e.getMessage());
        }
    }

    /**
     * Carrega o inventário do jogador a partir do banco.
     * Retorna null se o jogador ainda não tiver um registro de inventário.
     */
    public Inventario carregarInventario(int jogadorId) {
        Inventario inventario = null;
        String sql = """
            SELECT qtd_chaves, qtd_buffs_coletados, ultima_atualizacao
            FROM inventario_jogador
            WHERE jogador_id = ?
        """;

        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, jogadorId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    inventario = new Inventario();
                    inventario.setJogadorId(jogadorId);
                    inventario.setQtdChaves(rs.getInt("qtd_chaves"));
                    inventario.setQtdBuffsColetados(rs.getInt("qtd_buffs_coletados"));
                    Timestamp ts = rs.getTimestamp("ultima_atualizacao");
                    inventario.setUltimaAtualizacao(ts != null ? ts.toLocalDateTime() : LocalDateTime.now());
                }
            }

            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao carregar inventário: " + e.getMessage());
        }

        return inventario;
    }
}
