package br.com.celeratti.services;

import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginServices {

    public boolean verificarLogin(String email, String senha) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexao();
        Usuario usuario = new LoginDao().buscarUsuarios(email,senha,con);
        if(usuario == null){
            return false;
        }
        else if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
            return true;
        }
        return false;
    }
}
