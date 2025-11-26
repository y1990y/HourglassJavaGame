package banco.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    InfoConexao infoConexao = new InfoConexao();
    private Connection con;
    public Connection getConexao() {
        return con;
    }
    public Conexao() throws Exception {
        String url = infoConexao.getUrl();
        String usuario = infoConexao.getUsuario();
        String senha = infoConexao.getSenha();
        String driver = infoConexao.getDriver();
        Class.forName(driver);
        con = DriverManager.getConnection(url, usuario, senha);
    }
}
