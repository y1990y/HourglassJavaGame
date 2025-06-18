package banco;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private Connection con;
    public Connection getConexao() {
        return con;
    }
    public Conexao() throws Exception {
        String url = "jdbc:sqlserver://DESKTOP-BUKH5JT\\SQLEXPRESS:1433;databaseName=hourglass;encrypt=false;trustServerCertificate=true";
        String usuario = "y1990";
        String senha = "19901990";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(driver);
        con = DriverManager.getConnection(url, usuario, senha);
    }
}
