package com.hourglass.game.src.banco.entidadesBD;

public class Jogador extends Usuario {
    private String nomePersonagem;
    private int posicaoX;
    private int posicaoY;
    private int vida;
    private String dataSalvo;

    public Jogador() {}

    public Jogador(int id, String nome, String email, String senha, String createdAt, String updatedAt,
                   String nomePersonagem, int posicaoX, int posicaoY, int vida, String dataSalvo) {
        super(id, nome, email, senha, createdAt, updatedAt);
        this.nomePersonagem = nomePersonagem;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.vida = vida;
        this.dataSalvo = dataSalvo;
    }

    public String getNomePersonagem() { return nomePersonagem; }
    public void setNomePersonagem(String nomePersonagem) { this.nomePersonagem = nomePersonagem; }

    public int getPosicaoX() { return posicaoX; }
    public void setPosicaoX(int posicaoX) { this.posicaoX = posicaoX; }

    public int getPosicaoY() { return posicaoY; }
    public void setPosicaoY(int posicaoY) { this.posicaoY = posicaoY; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }

    public String getDataSalvo() { return dataSalvo; }
    public void setDataSalvo(String dataSalvo) { this.dataSalvo = dataSalvo; }
}
