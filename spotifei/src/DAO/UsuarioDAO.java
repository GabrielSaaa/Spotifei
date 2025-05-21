package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import util.ConexaoBanco;

public class UsuarioDAO {
    private Connection conexao;

    public UsuarioDAO() {
        conexao = ConexaoBanco.getConnection();
    }

    // Método para salvar um usuário no banco
    public boolean salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (usuario, senha) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            return false;
        }
    }

    // Método para verificar login
    public boolean validarUsuario(String usuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se encontrar usuário válido
        } catch (SQLException e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            return false;
        }
    }
}
