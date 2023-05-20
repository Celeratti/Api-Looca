package br.com.celeratti.services;

import br.com.celeratti.dao.ComponentesDAO;
import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.dao.MaquinaDao;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.dto.DadosUsuario;
import br.com.celeratti.model.EspecificacoesHardware;
import br.com.celeratti.util.Maquina;

import java.io.IOException;
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
        return new LoginDao().buscarUsuarios(email,senha);
    }

    public DadosMaquina verificarMaquina(String identificacao) {
            return maquinaDao.buscarMaquina(identificacao);
    }

    public void inserirEspecs(EspecificacoesHardware especificacoesHardware) {
        maquinaDao.enviarEspecsProBanco(especificacoesHardware);
    }

    public boolean verificarConexao() {
        String ipAddress = "www.google.com";
        try {
            Process process = Runtime.getRuntime().exec("ping " + ipAddress);
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void reiniciar() {
        String comando;
        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
        try {
            if (sistemaOperacional.contains("win")) {
                comando = "shutdown /r /t 0"; // Comando para reiniciar no Windows
            } else if (sistemaOperacional.contains("nix") || sistemaOperacional.contains("nux")
                    || sistemaOperacional.contains("mac")) {
                comando = "sudo shutdown -r now"; // Comando para reiniciar no Linux/Unix/Mac
            } else {
                throw new UnsupportedOperationException("Sistema operacional não suportado para reiniciar o computador.");
            }
            Runtime.getRuntime().exec(comando);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
