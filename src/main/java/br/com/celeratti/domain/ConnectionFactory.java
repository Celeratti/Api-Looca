package br.com.celeratti.domain;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    public Connection getConnection() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","root");
            return con;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



}
