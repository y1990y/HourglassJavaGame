package com.hourglass.game.src.banco.dao;

import com.hourglass.game.api.entity.InventarioJogadorEntity;
import com.hourglass.game.src.banco.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InventarioJogadorDAO {

    /**
     * Atualiza ou insere o inventário do jogador no banco.
     * Cria/atualiza duas linhas: 
     * item_id = 1 → qtd_chaves
     * item_id = 2 → qtd_buffs_coletados
     */
    public void salvarOuAtualizarInventario(InventarioJogadorEntity inventario) {
        try {
            Conexao conexao = new Conexao();
            Connection con = conexao.getConexao();

            // Garante valores válidos
            int qtdChaves = inventario.getQuantidadeAtual() >= 0 ? inventario.getQuantidadeAtual() : 0;
            int qtdBuffs  = inventario.getTotalColetado() >= 0 ? inventario.getTotalColetado() : 0;

            // Cria objetos separados para cada item
            InventarioJogadorEntity chave = new InventarioJogadorEntity();
            chave.setJogadorId(inventario.getJogadorId());
            chave.setItemId(1);
            chave.setQuantidadeAtual(qtdChaves);
            chave.setTotalColetado(0); // obrigatório, não pode ser null

            InventarioJogadorEntity buff = new InventarioJogadorEntity();
            buff.setJogadorId(inventario.getJogadorId());
            buff.setItemId(2);
            buff.setQuantidadeAtual(0); // não usado para buffs
            buff.setTotalColetado(qtdBuffs);

            // Salva cada item individualmente usando o novo método
            salvarOuAtualizarItem(con, chave);
            salvarOuAtualizarItem(con, buff);

            con.close();

        } catch (Exception e) {
            System.err.println("Erro ao atualizar inventário: " + e.getMessage());
        }
    }

    /**
     * Novo método: recebe o InventarioJogadorEntity e salva/atualiza
     */
    private void salvarOuAtualizarItem(Connection con, InventarioJogadorEntity inventario) throws Exception {
        String sql = """
            MERGE INTO inventario_jogador AS destino
            USING (SELECT ? AS jogador_id, ? AS item_id, ? AS quantidade_atual, ? AS total_coletado) AS origem
            ON destino.jogador_id = origem.jogador_id AND destino.item_id = origem.item_id
            WHEN MATCHED THEN
                UPDATE SET 
                    quantidade_atual = origem.quantidade_atual,
                    total_coletado = origem.total_coletado
            WHEN NOT MATCHED THEN
                INSERT (jogador_id, item_id, quantidade_atual, total_coletado)
                VALUES (origem.jogador_id, origem.item_id, origem.quantidade_atual, origem.total_coletado);
        """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, inventario.getJogadorId());
            ps.setInt(2, inventario.getItemId());
            ps.setInt(3, inventario.getQuantidadeAtual());
            ps.setInt(4, inventario.getTotalColetado());
            ps.executeUpdate();
        }
    }

    /**
     * Carrega o inventário completo do jogador.
     * Retorna um InventarioJogadorEntity com:
     * quantidadeAtual = qtd_chaves (item 1)
     * totalColetado = qtd_buffs (item 2)
     */
   public InventarioJogadorEntity carregarInventario(int jogadorId, int itemId) {
    InventarioJogadorEntity inv = new InventarioJogadorEntity();
    inv.setJogadorId(jogadorId);
    inv.setItemId(itemId);

    try {
        Conexao conexao = new Conexao();
        Connection con = conexao.getConexao();

        // Carrega quantidade de acordo com itemId
        int quantidade = carregarQuantidade(con, jogadorId, itemId);
        if (itemId == 1) {
            inv.setQuantidadeAtual(quantidade);       // chaves
        } else if (itemId == 2) {
            inv.setTotalColetado(quantidade);        // buffs
        }

        con.close();
    } catch (Exception e) {
        System.err.println("Erro ao carregar inventário: " + e.getMessage());
    }

    return inv;
}


    private int carregarQuantidade(Connection con, int jogadorId, int itemId) throws Exception {
        String sql = "SELECT quantidade_atual FROM inventario_jogador WHERE jogador_id = ? AND item_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, jogadorId);
            ps.setInt(2, itemId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantidade_atual");
            } else {
                return 0;
            }
        }
    }
}
