package com.hourglass.game.src.banco.entidadesBD;

public class SistemaTempo {
    private int id;
    private int jogadorId;
    private String estadoSalvo;
    private String dataCriacao;

    public SistemaTempo() {}

    public SistemaTempo(int id, int jogadorId, String estadoSalvo, String dataCriacao) {
        this.id = id;
        this.jogadorId = jogadorId;
        this.estadoSalvo = estadoSalvo;
        this.dataCriacao = dataCriacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getJogadorId() { return jogadorId; }
    public void setJogadorId(int jogadorId) { this.jogadorId = jogadorId; }
    public String getEstadoSalvo() { return estadoSalvo; }
    public void setEstadoSalvo(String estadoSalvo) { this.estadoSalvo = estadoSalvo; }
    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }
}