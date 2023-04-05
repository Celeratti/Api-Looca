package br.com.celeratti.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexão(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/celeratti?user=root&password=root");
        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
