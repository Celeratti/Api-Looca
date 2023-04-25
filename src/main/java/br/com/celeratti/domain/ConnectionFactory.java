package br.com.celeratti.domain;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionFactory {
    private JdbcTemplate connection;

    public ConnectionFactory() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.h2.Driver");
            dataSource.setUrl("jdbc:h2:file:./celeratti");
            dataSource.setUsername("sa");
            dataSource.setPassword("");
            this.connection = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConnection() {
        return connection;
    }



}
