package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Playlist;

public class PlaylistDAO {
    private Connection conexao;

    public PlaylistDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spotifei", "postgres", "esqueci05");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    public void criarPlaylist(String nome) {
        String sql = "INSERT INTO playlist (nome) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
        }
    }

    public void excluirPlaylist(int id) {
        String sql = "DELETE FROM playlist WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir playlist: " + e.getMessage());
        }
    }

    public List<Playlist> buscarPlaylists() {
        List<Playlist> lista = new ArrayList<>();
        String sql = "SELECT * FROM playlist ORDER BY nome ASC";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist(rs.getInt("id"), rs.getString("nome"));
                lista.add(playlist);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar playlists: " + e.getMessage());
        }
        return lista;
    }

    public void adicionarMusicaNaPlaylist(int playlistId, int musicaId) {
        String sql = "INSERT INTO playlist_musica (playlist_id, musica_id) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            stmt.setInt(2, musicaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar música na playlist: " + e.getMessage());
        }
    }

    public void removerMusicaDaPlaylist(int playlistId, int musicaId) {
        String sql = "DELETE FROM playlist_musica WHERE playlist_id = ? AND musica_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            stmt.setInt(2, musicaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover música da playlist: " + e.getMessage());
        }
    }

    public List<String> buscarMusicasDaPlaylist(int playlistId) {
        List<String> listaMusicas = new ArrayList<>();
        String sql = "SELECT m.nome, m.artista FROM playlist_musica pm " +
                     "JOIN musica m ON pm.musica_id = m.id " +
                     "WHERE pm.playlist_id = ? ORDER BY m.nome ASC";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listaMusicas.add(rs.getString("nome") + " - " + rs.getString("artista"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas da playlist: " + e.getMessage());
        }
        return listaMusicas;
    }
}
