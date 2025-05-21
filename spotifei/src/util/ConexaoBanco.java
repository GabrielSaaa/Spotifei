package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/spotifei"; 
    private static final String USUARIO = "postgres";
    private static final String SENHA = "esqueci05"; 

    public static Connection getConnection() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão bem-sucedida!");
            return conexao;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }

    public static void fecharConexao(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
                System.out.println("Conexão fechada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Testar conexão
        Connection conexao = ConexaoBanco.getConnection();
        ConexaoBanco.fecharConexao(conexao);
    }
}
