package br.com.celeratti.util;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import br.com.celeratti.services.Services;
import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

public class Maquina {
    private Long id;
    private Componentes componentes;
    private Looca looca;
    private JdbcTemplate con;
    private JdbcTemplate conAzure;
    private Services services;

//
//    public Maquina(Long idMaquina) {
//        this.looca = new Looca();
//        this.componentes = new Componentes(looca);
//        this.con = new ConnectionFactory().getConnection();
//        this.conAzure = new ConnectionFactory().getConnectionAzure();
//        this.services = new Services();
//        this.id = idMaquina;
//    }

    public Maquina() {
        this.looca = new Looca();
        this.componentes = new Componentes(looca);
        this.con = new ConnectionFactory().getConnection();
        this.conAzure = new ConnectionFactory().getConnectionAzure();
        this.services = new Services();
    }

    public Componentes getComponentes() {
        return componentes;
    }

    public Looca getLooca() {
        return looca;
    }

    public JdbcTemplate getCon() {
        return con;
    }
    public JdbcTemplate getConAzure() {
        return conAzure;
    }

    public Services getServices() {
        return services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
