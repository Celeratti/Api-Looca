package br.com.celeratti.dao;

import br.com.celeratti.model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class LoginDao {

    //Busca usuários com os dados que foram digitados na tela de login
    public List<Usuario> buscarUsuarios(String email, String senha, JdbcTemplate con) {
        List<Usuario> usuario;
        usuario = con.query("SELECT email, senha FROM funcionario WHERE email = ? and senha = ?;",new BeanPropertyRowMapper(Usuario.class),email,senha);

        return usuario;
    }
}
