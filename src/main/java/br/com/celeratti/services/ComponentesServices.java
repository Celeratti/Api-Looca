package br.com.celeratti.services;

import br.com.celeratti.DAO.ComponentesDAO;
import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;

import java.sql.Connection;
import java.sql.SQLException;

public class ComponentesServices {
    private ConnectionFactory connection;

    public ComponentesServices() {
        this.connection = new ConnectionFactory();
    }

    public void enviarProBanco(Componentes componentes){
        Connection con = connection.recuperarConexão();
        new ComponentesDAO(con).inserir(componentes);
    }
}
