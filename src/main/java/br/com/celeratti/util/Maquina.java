package br.com.celeratti.util;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import br.com.celeratti.services.Services;
import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class Maquina {
    private int id;
    private Componentes componentes;
    private Looca looca;
    private JdbcTemplate con;
    private Services services;

    public Maquina() {
        this.id = 1;
        this.looca = new Looca();
        this.componentes = new Componentes(looca);
        this.con = new ConnectionFactory().getConnection();
        this.services = new Services();
    }


    public int getId() {
        return id;
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

    public Services getServices() {
        return services;
    }
}
