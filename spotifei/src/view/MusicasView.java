package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.MusicaDAO;
import model.Musica;

public class MusicasView extends JFrame {
    private JTextField txtBusca;
    private JButton btnBuscar, btnCurtir, btnDescurtir;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaMusicas;
    private MusicaDAO musicaDAO;

    public MusicasView() {
        musicaDAO = new MusicaDAO();

        setTitle("Spotifei - Músicas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelBusca = new JPanel();
        txtBusca = new JTextField(25);
        btnBuscar = new JButton("Buscar");

        painelBusca.add(txtBusca);
        painelBusca.add(btnBuscar);

        modeloLista = new DefaultListModel<>();
        listaMusicas = new JList<>(modeloLista);
        JScrollPane painelLista = new JScrollPane(listaMusicas);

        btnCurtir = new JButton("Curtir");
        btnDescurtir = new JButton("Descurtir");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnCurtir);
        painelBotoes.add(btnDescurtir);

        add(painelBusca, BorderLayout.NORTH);
        add(painelLista, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarMusicas();
            }
        });

        btnCurtir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                curtirMusica();
            }
        });

        btnDescurtir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                descurtirMusica();
            }
        });

        buscarMusicas();

        setVisible(true);
    }

    private void buscarMusicas() {
        modeloLista.clear();
        String termo = txtBusca.getText().trim();

        List<Musica> musicas = termo.isEmpty() ? musicaDAO.buscarTodasMusicas() : musicaDAO.buscarMusicas(termo);

        for (Musica musica : musicas) {
            String statusCurtida = musica.isCurtida() ? "❤️" : "🤍";
            modeloLista.addElement(statusCurtida + " " + musica.getNome() + " - " + musica.getArtista() + " (" + musica.getGenero() + ")");
        }
    }

private void curtirMusica() {
    int indiceSelecionado = listaMusicas.getSelectedIndex();
    if (indiceSelecionado != -1) {
        List<Musica> musicas = txtBusca.getText().trim().isEmpty() ? musicaDAO.buscarTodasMusicas() : musicaDAO.buscarMusicas(txtBusca.getText());
        if (indiceSelecionado < musicas.size()) {
            int idMusica = musicas.get(indiceSelecionado).getId();
            musicaDAO.curtirMusica(idMusica);
            buscarMusicas(); 
        }
    }
}

private void descurtirMusica() {
    int indiceSelecionado = listaMusicas.getSelectedIndex();
    if (indiceSelecionado != -1) {
        List<Musica> musicas = txtBusca.getText().trim().isEmpty() ? musicaDAO.buscarTodasMusicas() : musicaDAO.buscarMusicas(txtBusca.getText());
        if (indiceSelecionado < musicas.size()) {
            int idMusica = musicas.get(indiceSelecionado).getId();
            musicaDAO.descurtirMusica(idMusica);
            buscarMusicas(); 
        }
    }
}

    public static void main(String[] args) {
        new MusicasView();
    }
}
