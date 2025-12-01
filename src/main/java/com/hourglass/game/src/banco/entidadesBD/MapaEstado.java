package com.hourglass.game.src.banco.entidadesBD;

public class MapaEstado {
    private int id;
    private int jogadorId;
    private String mapaNome;
    private String portaId;
    private boolean portaAberta;
    private String itemId;
    private boolean itemColetado;

    public MapaEstado() {}

    public MapaEstado(int id, int jogadorId, String mapaNome, String portaId, boolean portaAberta, String itemId, boolean itemColetado) {
        this.id = id;
        this.jogadorId = jogadorId;
        this.mapaNome = mapaNome;
        this.portaId = portaId;
        this.portaAberta = portaAberta;
        this.itemId = itemId;
        this.itemColetado = itemColetado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getJogadorId() { return jogadorId; }
    public void setJogadorId(int jogadorId) { this.jogadorId = jogadorId; }
    public String getMapaNome() { return mapaNome; }
    public void setMapaNome(String mapaNome) { this.mapaNome = mapaNome; }
    public String getPortaId() { return portaId; }
    public void setPortaId(String portaId) { this.portaId = portaId; }
    public boolean isPortaAberta() { return portaAberta; }
    public void setPortaAberta(boolean portaAberta) { this.portaAberta = portaAberta; }
    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    public boolean isItemColetado() { return itemColetado; }
    public void setItemColetado(boolean itemColetado) { this.itemColetado = itemColetado; }
}