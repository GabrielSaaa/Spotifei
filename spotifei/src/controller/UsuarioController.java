package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean cadastrarUsuario(String usuario, String senha) {
        Usuario novoUsuario = new Usuario(usuario, senha);
        return usuarioDAO.salvar(novoUsuario);
    }

    public boolean verificarLogin(String usuario, String senha) {
        return usuarioDAO.validarUsuario(usuario, senha);
    }
}
