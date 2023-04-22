package br.com.celeratti.services;

import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class LoginServices {

    public boolean verificarLogin(String email, String senha) {
        JdbcTemplate con = new ConnectionFactory().getConnection();
        List<Usuario> usuario = new LoginDao().buscarUsuarios(email,senha,con);
        for (int i = 0;i<usuario.size();i++) {
            if(usuario == null){
                return false;
            } else if (usuario.get(i).getEmail().equals(email) && usuario.get(i).getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }
}
