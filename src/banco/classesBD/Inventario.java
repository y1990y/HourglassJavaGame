package banco.classesBD;

import java.time.LocalDateTime;

import banco.dao.InventarioDAO;

public class Inventario {

    private int jogadorId;
    private int qtdChaves;
    private int qtdBuffsColetados;
    private LocalDateTime ultimaAtualizacao;

    public Inventario() {}

    public Inventario(int jogadorId, int qtdChaves, int qtdBuffsColetados, LocalDateTime ultimaAtualizacao) {
        this.jogadorId = jogadorId;
        this.qtdChaves = qtdChaves;
        this.qtdBuffsColetados = qtdBuffsColetados;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public int getJogadorId() {
        return jogadorId;
    }

    public void setJogadorId(int jogadorId) {
        this.jogadorId = jogadorId;
    }

    public int getQtdChaves() {
        return qtdChaves;
    }

    public void setQtdChaves(int qtdChaves) {
        this.qtdChaves = qtdChaves;
    }

    public int getQtdBuffsColetados() {
        return qtdBuffsColetados;
    }

    public void setQtdBuffsColetados(int qtdBuffsColetados) {
        this.qtdBuffsColetados = qtdBuffsColetados;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public void atualizaInventario() {
    new InventarioDAO().salvarOuAtualizarInventario(this);
    }

    @Override
    public String toString() {
        return "Inventario {" +
                "jogadorId=" + jogadorId +
                ", qtdChaves=" + qtdChaves +
                ", qtdBuffsColetados=" + qtdBuffsColetados +
                ", ultimaAtualizacao=" + ultimaAtualizacao +
                '}';
    }
}
