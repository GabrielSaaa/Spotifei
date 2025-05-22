package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {
    private Connection conexao;

    public HistoricoDAO() {
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/spotifei", "postgres", "esqueci05");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    public void registrarBusca(int musicaId) {
        String sqlCheck = "SELECT id FROM musica WHERE id = ?";
        String sqlInsert = "INSERT INTO historico (musica_id, tipo, data_hora) VALUES (?, 'busca', NOW())";

        try (PreparedStatement checkStmt = conexao.prepareStatement(sqlCheck)) {
            checkStmt.setInt(1, musicaId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                try (PreparedStatement insertStmt = conexao.prepareStatement(sqlInsert)) {
                    insertStmt.setInt(1, musicaId);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao registrar busca: " + e.getMessage());
        }
    }

    public void registrarCurtida(int musicaId) {
        String sql = "INSERT INTO historico (musica_id, tipo, data_hora) VALUES (?, 'curtida', NOW())";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, musicaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar curtida no histórico: " + e.getMessage());
        }
    }

    public void registrarDescurtida(int musicaId) {
        String sql = "INSERT INTO historico (musica_id, tipo, data_hora) VALUES (?, 'descurtida', NOW())";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, musicaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao registrar descurtida no histórico: " + e.getMessage());
        }
    }

    public List<String> buscarCurtidas() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nome, artista FROM musica WHERE curtida = TRUE ORDER BY nome ASC";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getString("nome") + " - " + rs.getString("artista"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas curtidas: " + e.getMessage());
        }
        return lista;
    }

    public List<String> buscarNaoCurtidas() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nome, artista FROM musica WHERE curtida = FALSE ORDER BY nome ASC";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getString("nome") + " - " + rs.getString("artista"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar músicas não curtidas: " + e.getMessage());
        }
        return lista;
    }

    public List<String> buscarHistoricoBuscas() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT m.nome, m.artista FROM historico h " +
                     "JOIN musica m ON h.musica_id = m.id " +
                     "WHERE h.tipo = 'busca' ORDER BY h.data_hora DESC LIMIT 10";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(rs.getString("nome") + " - " + rs.getString("artista"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar histórico de buscas: " + e.getMessage());
        }
        return lista;
    }
}

