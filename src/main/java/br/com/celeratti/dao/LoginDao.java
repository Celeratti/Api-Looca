package br.com.celeratti.dao;

import br.com.celeratti.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDao {

    public Usuario buscarUsuarios(String email, String senha, Connection con) {
        String sql = "SELECT email, senha FROM funcionario WHERE email = ? and senha = ?;";
        Usuario usuario = null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2,senha);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String emailRs = rs.getString(1);
                String senhaRs = rs.getString(2);
                usuario = new Usuario(emailRs,senhaRs);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
