package com.hourglass.game.src.banco.conexao;

public class InfoConexao {

    private String url = "jdbc:sqlserver://DESKTOP-NERCJSV\\SQLEXPRESS:1433;databaseName=hourglassteste2;encrypt=false;trustServerCertificate=true";
    private String usuario = "sa";
    private String senha = "Esegredo.159";
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getDriver() {
        return driver;
    }
}