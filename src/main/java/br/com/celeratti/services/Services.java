package br.com.celeratti.services;

import br.com.celeratti.dao.ComponentesDAO;
import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.dao.MaquinaDao;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.dto.DadosUsuario;
import br.com.celeratti.model.EspecificacoesHardware;
import br.com.celeratti.util.Maquina;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        try {
            componentesDAO.inserirDadosComponentes(maquina);
        } catch (IOException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DadosUsuario verificarLogin(String email, String senha) {
        return loginDao.buscarUsuarios(email,senha);
    }

    public DadosMaquina verificarMaquina(String identificacao) {
            return maquinaDao.buscarMaquina(identificacao);
    }

    public void inserirEspecs(EspecificacoesHardware especificacoesHardware) {
        maquinaDao.enviarEspecsProBanco(especificacoesHardware);
    }

    public boolean verificarConexao() {
        try {
            System.out.println("Enviando requisição para verificar conexão com servidor");
            URL url = new URL("https://spring-azure--demo.azurewebsites.net/home/conexao");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Conexão bem sucedida");
                return true;
            } else {
                System.out.println("Conexão mal sucedida");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void reiniciar() {
        try {
            String comando = "sudo shutdown -r now";
            Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
