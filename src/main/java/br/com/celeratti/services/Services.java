package br.com.celeratti.services;

import br.com.celeratti.dao.ComponentesDAO;
import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.dao.MaquinaDao;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.dto.DadosUsuario;
import br.com.celeratti.model.EspecificacoesHardware;
import br.com.celeratti.util.Maquina;


public class Services {
    private MaquinaDao maquinaDao;
    private LoginDao loginDao;
    private ComponentesDAO componentesDAO;

    public Services() {
        this.maquinaDao = new MaquinaDao();
        this.loginDao = new LoginDao();
        this.componentesDAO = new ComponentesDAO();
    }

    //Envia os dados dos componenetes para a classe DAO e insere no banco
    public void enviarProBanco(Maquina maquina){
        componentesDAO.inserirDadosComponentes(maquina);
    }

    public DadosUsuario verificarLogin(String email, String senha) {
        return new LoginDao().buscarUsuarios(email,senha);
    }

    public DadosMaquina verificarMaquina(String identificacao) {
            return maquinaDao.buscarMaquina(identificacao);
    }

    public void inserirEspecs(EspecificacoesHardware especificacoesHardware) {
        maquinaDao.enviarEspecsProBanco(especificacoesHardware);
    }
}
