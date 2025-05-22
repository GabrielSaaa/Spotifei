package view;

import javax.swing.*;
import java.awt.*;
import dao.HistoricoDAO;
import java.util.List;

public class HistoricoView extends JFrame {
    private JTabbedPane abas;
    private DefaultListModel<String> modeloCurtidas, modeloNaoCurtidas;
    private JList<String> listaCurtidas, listaNaoCurtidas;
    private HistoricoDAO historicoDAO;
    private DefaultListModel<String> modeloBuscas;
    private JList<String> listaBuscas;
    
    public HistoricoView() {
        historicoDAO = new HistoricoDAO();

        setTitle("Spotifei - Histórico");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        abas = new JTabbedPane();

        modeloCurtidas = new DefaultListModel<>();
        listaCurtidas = new JList<>(modeloCurtidas);
        JScrollPane painelCurtidas = new JScrollPane(listaCurtidas);

        modeloNaoCurtidas = new DefaultListModel<>();
        listaNaoCurtidas = new JList<>(modeloNaoCurtidas);
        JScrollPane painelNaoCurtidas = new JScrollPane(listaNaoCurtidas);

        modeloBuscas = new DefaultListModel<>();
        listaBuscas = new JList<>(modeloBuscas);
        JScrollPane painelBuscas = new JScrollPane(listaBuscas);

        abas.addTab("Buscas Recentes", painelBuscas);        
        abas.addTab("Curtidas", painelCurtidas);
        abas.addTab("Não Curtidas", painelNaoCurtidas);

        add(abas, BorderLayout.CENTER);

        carregarHistorico();
        
        setVisible(true);
    }
  
 private void carregarHistorico() {
    modeloCurtidas.clear();
    modeloNaoCurtidas.clear();
    modeloBuscas.clear();

    List<String> curtidas = historicoDAO.buscarCurtidas();
    List<String> naoCurtidas = historicoDAO.buscarNaoCurtidas();
    List<String> buscas = historicoDAO.buscarHistoricoBuscas();

    curtidas.forEach(modeloCurtidas::addElement);
    naoCurtidas.forEach(modeloNaoCurtidas::addElement);
    buscas.forEach(modeloBuscas::addElement);
}


    public static void main(String[] args) {
        new HistoricoView();
    }
}


