package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.PlaylistDAO;
import dao.MusicaDAO;
import model.Playlist;
import model.Musica;

public class PlaylistView extends JFrame {
    private JTextField txtNomePlaylist;
    private JButton btnCriarPlaylist, btnExcluirPlaylist, btnAdicionarMusica, btnRemoverMusica;
    private DefaultListModel<String> modeloPlaylists, modeloMusicasDisponiveis, modeloMusicasPlaylist;
    private JList<String> listaPlaylists, listaMusicasDisponiveis, listaMusicasPlaylist;
    private PlaylistDAO playlistDAO;
    private MusicaDAO musicaDAO;

    public PlaylistView() {
        playlistDAO = new PlaylistDAO();
        musicaDAO = new MusicaDAO();

        setTitle("Spotifei - Playlists");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        txtNomePlaylist = new JTextField(20);
        btnCriarPlaylist = new JButton("Criar Playlist");
        btnExcluirPlaylist = new JButton("Excluir Playlist");

        painelSuperior.add(new JLabel("Nome da Playlist:"));
        painelSuperior.add(txtNomePlaylist);
        painelSuperior.add(btnCriarPlaylist);
        painelSuperior.add(btnExcluirPlaylist);

        modeloPlaylists = new DefaultListModel<>();
        listaPlaylists = new JList<>(modeloPlaylists);
        JScrollPane painelListaPlaylists = new JScrollPane(listaPlaylists);

        modeloMusicasDisponiveis = new DefaultListModel<>();
        listaMusicasDisponiveis = new JList<>(modeloMusicasDisponiveis);
        JScrollPane painelListaMusicasDisponiveis = new JScrollPane(listaMusicasDisponiveis);

        modeloMusicasPlaylist = new DefaultListModel<>();
        listaMusicasPlaylist = new JList<>(modeloMusicasPlaylist);
        JScrollPane painelListaMusicasPlaylist = new JScrollPane(listaMusicasPlaylist);

        btnAdicionarMusica = new JButton("Adicionar Música");
        btnRemoverMusica = new JButton("Remover Música");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionarMusica);
        painelBotoes.add(btnRemoverMusica);

        JPanel painelCentral = new JPanel(new GridLayout(1, 3));
        painelCentral.add(painelListaPlaylists);
        painelCentral.add(painelListaMusicasDisponiveis);
        painelCentral.add(painelListaMusicasPlaylist);

        add(painelSuperior, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnCriarPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarPlaylist();
            }
        });

        btnExcluirPlaylist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excluirPlaylist();
            }
        });

        btnAdicionarMusica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarMusica();
            }
        });

        btnRemoverMusica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removerMusica();
            }
        });

        listaPlaylists.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                carregarMusicasPlaylist();
            }
        });

        carregarPlaylists();
        carregarMusicasDisponiveis();

        setVisible(true);
    }

    private void carregarPlaylists() {
        modeloPlaylists.clear();
        List<Playlist> playlists = playlistDAO.buscarPlaylists();
        for (Playlist playlist : playlists) {
            modeloPlaylists.addElement(playlist.getNome());
        }
    }

    private void carregarMusicasDisponiveis() {
        modeloMusicasDisponiveis.clear();
        List<Musica> musicas = musicaDAO.buscarTodasMusicas();
        for (Musica musica : musicas) {
            modeloMusicasDisponiveis.addElement(musica.getNome() + " - " + musica.getArtista());
        }
    }

    private void carregarMusicasPlaylist() {
        modeloMusicasPlaylist.clear();
        int indicePlaylist = listaPlaylists.getSelectedIndex();

        if (indicePlaylist != -1) {
            List<Playlist> playlists = playlistDAO.buscarPlaylists();
            int playlistId = playlists.get(indicePlaylist).getId();
            List<String> musicasDaPlaylist = playlistDAO.buscarMusicasDaPlaylist(playlistId);

            for (String musica : musicasDaPlaylist) {
                modeloMusicasPlaylist.addElement(musica);
            }
        }
    }

    private void criarPlaylist() {
        String nome = txtNomePlaylist.getText().trim();
        if (!nome.isEmpty()) {
            playlistDAO.criarPlaylist(nome);
            carregarPlaylists();
            txtNomePlaylist.setText("");
        }
    }

    private void excluirPlaylist() {
        int indiceSelecionado = listaPlaylists.getSelectedIndex();
        if (indiceSelecionado != -1) {
            List<Playlist> playlists = playlistDAO.buscarPlaylists();
            playlistDAO.excluirPlaylist(playlists.get(indiceSelecionado).getId());
            carregarPlaylists();
            modeloMusicasPlaylist.clear();
        }
    }

private void adicionarMusica() {
    int indicePlaylist = listaPlaylists.getSelectedIndex();
    int indiceMusica = listaMusicasDisponiveis.getSelectedIndex();

    if (indicePlaylist != -1 && indiceMusica != -1) {
        List<Playlist> playlists = playlistDAO.buscarPlaylists();
        List<Musica> musicas = musicaDAO.buscarTodasMusicas();
        int playlistId = playlists.get(indicePlaylist).getId();
        int musicaId = musicas.get(indiceMusica).getId();

        List<String> musicasDaPlaylist = playlistDAO.buscarMusicasDaPlaylist(playlistId);

        boolean jaExiste = musicasDaPlaylist.stream().anyMatch(m -> m.contains(musicas.get(indiceMusica).getNome()));

        if (!jaExiste) {
            playlistDAO.adicionarMusicaNaPlaylist(playlistId, musicaId);
            carregarMusicasPlaylist();
        } else {
            JOptionPane.showMessageDialog(this, "Essa música já está na playlist!");
        }
    }
}
private void removerMusica() {
    int indicePlaylist = listaPlaylists.getSelectedIndex();
    int indiceMusica = listaMusicasPlaylist.getSelectedIndex();

    if (indicePlaylist != -1 && indiceMusica != -1) {
        List<Playlist> playlists = playlistDAO.buscarPlaylists();
        int playlistId = playlists.get(indicePlaylist).getId();

        List<Musica> musicas = musicaDAO.buscarTodasMusicas();
        List<String> musicasDaPlaylist = playlistDAO.buscarMusicasDaPlaylist(playlistId);
        
        Musica musicaSelecionada = musicas.stream()
            .filter(m -> (m.getNome() + " - " + m.getArtista()).equals(musicasDaPlaylist.get(indiceMusica)))
            .findFirst()
            .orElse(null);

        if (musicaSelecionada != null) {
            playlistDAO.removerMusicaDaPlaylist(playlistId, musicaSelecionada.getId());
            carregarMusicasPlaylist();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao remover música: ID não encontrado.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Selecione uma música antes de tentar removê-la.");
    }
}


    public static void main(String[] args) {
        new PlaylistView();
    }
}
