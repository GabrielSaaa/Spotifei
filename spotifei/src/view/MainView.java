package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    public MainView() {
        setTitle("Spotifei - Home");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnMusicas = new JButton("Músicas");
        JButton btnPlaylists = new JButton("Playlists");
        JButton btnHistorico = new JButton("Histórico");

        btnMusicas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MusicasView();
            }
        });


        btnPlaylists.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PlaylistView();
            }
        });

        btnHistorico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HistoricoView();
            }
        });

        add(btnMusicas);
        add(btnPlaylists);
        add(btnHistorico);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }
}
