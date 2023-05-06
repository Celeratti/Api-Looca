package br.com.celeratti.services;

import br.com.celeratti.dao.ComponentesDAO;
import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.dao.MaquinaDao;
import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.dto.DadosUsuario;
import br.com.celeratti.model.EspecificacoesHardware;
import br.com.celeratti.model.Usuario;
import br.com.celeratti.util.Maquina;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.List;

public class Services {

    //Envia os dados dos componenetes para a classe DAO e insere no banco
    public void enviarProBanco(Maquina maquina){
        new ComponentesDAO().inserirDadosComponentes(maquina);
    }

    public DadosUsuario verificarLogin(String email, String senha) {
        return new LoginDao().buscarUsuarios(email,senha);
    }

    public DadosMaquina verificarMaquina(String identificacao) {
            return new MaquinaDao().buscarMaquina(identificacao);
    }

    public void inserirEspecs(EspecificacoesHardware especificacoesHardware) {
        new MaquinaDao().enviarEspecsProBanco(especificacoesHardware);
    }
}
