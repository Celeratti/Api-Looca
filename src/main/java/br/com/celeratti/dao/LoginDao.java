package br.com.celeratti.dao;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.dto.DadosUsuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LoginDao {
    private Connection con;

//    public LoginDao() {
//        this.con = new ConnectionFactory().getConnectionAzure();
//    }
    public LoginDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    //    Busca usuários com os dados que foram digitados na tela de login
    public DadosUsuario buscarUsuarios(String email, String senha) {
        DadosUsuario dadosUsuario = null;
        String sql = "SELECT email, senha,esta_ativo,fkEmpresa FROM funcionario WHERE email = ? and senha = ?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,senha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
            String emailRecuperado = rs.getString(1);
            String senhaRecuperada = rs.getString(2);
            int status = rs.getInt(3);
            int fkEmpresa = rs.getInt(4);
            dadosUsuario = new DadosUsuario(emailRecuperado,senhaRecuperada,status,fkEmpresa);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dadosUsuario;
    }
}
