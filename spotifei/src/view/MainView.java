package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    public MainView() {
        setTitle("Spotifei - Tela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnMusicas = new JButton("Minhas Músicas");
        JButton btnPlaylists = new JButton("Minhas Playlists");
        JButton btnHistorico = new JButton("Histórico de Reprodução");

        btnMusicas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrindo Minhas Músicas...");
            }
        });

        btnPlaylists.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrindo Minhas Playlists...");
            }
        });

        btnHistorico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrindo Histórico de Reprodução...");
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
