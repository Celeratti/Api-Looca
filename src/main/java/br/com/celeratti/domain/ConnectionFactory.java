package br.com.celeratti.domain;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/celeratti?user=root&password=root");
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }


}
