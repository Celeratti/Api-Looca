package br.com.celeratti.dao;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.dto.DadosUsuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDao {
    private JdbcTemplate con;

    //    public LoginDao() {
//        this.con = new ConnectionFactory().getConnectionAzure();
//    }
    public LoginDao() {
        this.con = new ConnectionFactory().getConnectionAzure();
    }

    //    Busca usuários com os dados que foram digitados na tela de login
    public DadosUsuario buscarUsuarios(String email, String senha) {
        DadosUsuario dadosUsuario = null;
//
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1,email);
//            ps.setString(2,senha);
        dadosUsuario = con.queryForObject("SELECT email, senha,esta_ativo,fkEmpresa FROM funcionario WHERE email = ? and senha = ?;",new BeanPropertyRowMapper<DadosUsuario>(DadosUsuario.class),email,senha);
        return dadosUsuario;
    }
}
