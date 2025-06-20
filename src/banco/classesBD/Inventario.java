package banco.classesBD;

public class Inventario {
    private int id;
    private int jogadorId;
    private int itemId;
    private int quantidade;

    public Inventario() {}

    public Inventario(int id, int jogadorId, int itemId, int quantidade) {
        this.id = id;
        this.jogadorId = jogadorId;
        this.itemId = itemId;
        this.quantidade = quantidade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getJogadorId() { return jogadorId; }
    public void setJogadorId(int jogadorId) { this.jogadorId = jogadorId; }
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}