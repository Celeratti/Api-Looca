package br.com.celeratti.util;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import br.com.celeratti.services.ComponentesServices;
import com.github.britooo.looca.api.core.Looca;

import java.sql.Connection;

public class Maquina {
    private static int id;

    private Componentes componentes;
    private Looca looca;
    private Connection con;
    private ComponentesServices services;

    public Maquina() {
        this.id = 1;
        this.looca = new Looca();
        this.componentes = new Componentes(looca);
        this.con = new ConnectionFactory().recuperarConexao();
        this.services = new ComponentesServices();
    }


    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Componentes getComponentes() {
        return componentes;
    }

    public void setComponentes(Componentes componentes) {
        this.componentes = componentes;
    }

    public Looca getLooca() {
        return looca;
    }

    public void setLooca(Looca looca) {
        this.looca = looca;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public ComponentesServices getServices() {
        return services;
    }

    public void setServices(ComponentesServices services) {
        this.services = services;
    }
}
