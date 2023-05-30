package br.com.celeratti.domain;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


public class ConnectionFactory {

    public JdbcTemplate getConnection() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/celeratti?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            return new JdbcTemplate(dataSource);
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados MySQL no container Docker");
            return null;
        }
    }

    public JdbcTemplate getConnectionAzure() {
        try {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource.setUrl("jdbc:sqlserver://server-celeratti.database.windows.net:1433;database=celeratti;user=admin-celeratti@server-celeratti;password=#Gfgrupo7;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            return new JdbcTemplate(dataSource);
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados SQL Server");
            return null;
        }
    }
}
