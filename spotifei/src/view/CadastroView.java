package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.UsuarioController;

public class CadastroView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnCadastrar;

    public CadastroView() {
        setTitle("Spotifei - Cadastro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuário:");
        txtUsuario = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField(20);
        btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        add(lblUsuario); add(txtUsuario);
        add(lblSenha); add(txtSenha);
        add(new JLabel()); add(btnCadastrar);

        setVisible(true);
    }

    private void cadastrarUsuario() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioController usuarioController = new UsuarioController();
        boolean sucesso = usuarioController.cadastrarUsuario(usuario, senha);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginView();                        
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CadastroView();
    }
}
