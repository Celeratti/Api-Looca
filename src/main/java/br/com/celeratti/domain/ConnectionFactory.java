package br.com.celeratti.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    public Connection getConnection() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/celeratti?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false","root",
                    "pinguim");
            return con;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
//public Connection getConnection() {
//    try{
//        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/celeratti","root",
//                "root");
//        return con;
//    } catch (SQLException e){
//        throw new RuntimeException(e);
//    }
//}

    public Connection getConnectionAzure() {
        try{
            return DriverManager.getConnection("jdbc:sqlserver://server-celeratti.database.windows.net:1433;database=celeratti;user=admin-celeratti@server-celeratti;password=#Gfgrupo7;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }



}
