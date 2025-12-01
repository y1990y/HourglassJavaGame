package com.hourglass.game.src.banco.entidadesBD;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String createdAt;
    private String updatedAt;

    public Usuario() {}

    public Usuario(int id, String nome, String email, String senha, String createdAt, String updatedAt) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
}
