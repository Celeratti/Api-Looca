package br.com.celeratti.services;

import br.com.celeratti.dao.ComponentesDAO;
import br.com.celeratti.dao.LoginDao;
import br.com.celeratti.domain.ConnectionFactory;
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

    //    public void enviarProBanco(EspecificacoesHardware especs, Connection con){
    //        new ComponentesDAO(con).inserir(especs);
    //    }

//    public boolean verificarLogin(String email, String senha) {
//        Connection con = new ConnectionFactory().getConnection();
////        List<Usuario> usuario = new LoginDao().buscarUsuarios(email,senha,con);
//        for (int i = 0;i<usuario.size();i++) {
//            if(usuario == null){
//                return false;
//            } else if (usuario.get(i).getEmail().equals(email) && usuario.get(i).getSenha().equals(senha)){
//                return true;
//            }
//        }
//        return false;
//    }
}
