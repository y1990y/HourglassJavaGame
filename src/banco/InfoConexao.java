package banco;

public class InfoConexao {

    private String url = "jdbc:sqlserver://DESKTOP-BUKH5JT\\SQLEXPRESS:1433;databaseName=hourglass;encrypt=false;trustServerCertificate=true";
    private String usuario = "y1990";
    private String senha = "19901990";
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
