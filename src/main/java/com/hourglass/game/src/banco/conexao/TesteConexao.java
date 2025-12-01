package com.hourglass.game.src.banco.conexao;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            System.out.println("Conectando...");
            Conexao conexao = new Conexao();
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
    }
}
