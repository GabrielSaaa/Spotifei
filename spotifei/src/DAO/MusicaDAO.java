package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Musica;

public class MusicaDAO {
    private Connection conexao;

    public MusicaDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spotifei", "postgres", "esqueci05");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    public List<Musica> buscarTodasMusicas() {
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musica ORDER BY curtida DESC, nome ASC";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica musica = new Musica(rs.getString("nome"), rs.getString("artista"), rs.getString("genero"), rs.getBoolean("curtida"));
                musica.setId(rs.getInt("id"));
                lista.add(musica);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar todas as músicas: " + e.getMessage());
        }
        return lista;
    }

    public List<Musica> buscarMusicas(String termo) {
        List<Musica> lista = new ArrayList<>();
        String sql = "SELECT * FROM musica WHERE nome ILIKE ? OR artista ILIKE ? OR genero ILIKE ? ORDER BY curtida DESC, nome ASC";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%" + termo + "%");
            stmt.setString(2, "%" + termo + "%");
            stmt.setString(3, "%" + termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Musica musica = new Musica(rs.getString("nome"), rs.getString("artista"), rs.getString("genero"), rs.getBoolean("curtida"));
                musica.setId(rs.getInt("id"));
                lista.add(musica);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas: " + e.getMessage());
        }
        return lista;
    }

public void curtirMusica(int id) {
    String sql = "UPDATE musica SET curtida = TRUE WHERE id = ?";
    try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
        stmt.setInt(1, id);
        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas == 0) {
            System.out.println("Nenhuma música foi atualizada. Verifique se o ID existe.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao curtir música: " + e.getMessage());
    }
}


    public void descurtirMusica(int id) {
        String sql = "UPDATE musica SET curtida = FALSE WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao descurtir música: " + e.getMessage());
        }
    }
}
