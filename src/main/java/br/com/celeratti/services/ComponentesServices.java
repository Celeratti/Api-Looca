package br.com.celeratti.services;

import br.com.celeratti.dao.ComponentesDAO;
import br.com.celeratti.util.Maquina;


public class ComponentesServices {


    public void enviarProBanco(Maquina maquina){
        new ComponentesDAO(maquina.getCon()).inserirDadosComponentes(maquina);
    }


}
