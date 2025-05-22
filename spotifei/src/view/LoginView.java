package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UsuarioController;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Spotifei - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(20);
        btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(txtSenha);
        add(new JLabel());
        add(btnLogin);

        setVisible(true);
    }

    private void autenticarUsuario() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        UsuarioController usuarioController = new UsuarioController();
        boolean autenticado = usuarioController.verificarLogin(usuario, senha);

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MainView();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
