package br.com.celeratti.services;

import br.com.celeratti.DAO.ComponentesDAO;
import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import java.sql.Connection;

public class ComponentesServices {


    public void enviarProBanco(Componentes componentes, Connection con){
        System.out.println("Entrei no metódo 'enviarProBanco'");
        new ComponentesDAO(con).inserir(componentes);
        System.out.println("Voltei do método inserir");
    }
}
