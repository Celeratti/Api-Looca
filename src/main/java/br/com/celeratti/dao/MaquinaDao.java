package br.com.celeratti.dao;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.model.EspecificacoesHardware;
import br.com.celeratti.swing.TelaLogin;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaquinaDao {

    private final Connection con;

    public MaquinaDao() {
        this.con = new ConnectionFactory().getConnectionAzure();
    }

//    public MaquinaDao() {
//        this.con = new ConnectionFactory().getConnection();
//    }

    public DadosMaquina buscarMaquina(String identificacao) {
        DadosMaquina dados = null;
        String sql = "SELECT m.id,m.nomeIdentificador,s.status,m.fkempresa from maquina as m JOIN status as s on m.fkStatus = s.id WHERE nomeIdentificador = ?;";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,identificacao);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong(1);
                String nomeIdentificador = rs.getString(2);
                String status = rs.getString(3);
                int fkEmpresa = rs.getInt(4);
                dados = new DadosMaquina(id,nomeIdentificador,status,fkEmpresa);
            }
            rs.close();
            ps.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dados;
    }

    public void enviarEspecsProBanco(EspecificacoesHardware especificacoesHardware) {
        String sql = "INSERT INTO especificacoesHardware(memoriaTotal, tamanhoDisco, processador, cpuTotal, ipv4, ipv6, fkMaquina) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, Double.parseDouble(especificacoesHardware.getMemoriaTotal().toString()));
            ps.setDouble(2,especificacoesHardware.getTamanhoDisco());
            ps.setString(3,especificacoesHardware.getProcessador());
            ps.setLong(4, especificacoesHardware.getCpuFrequencia());
            ps.setString(5,especificacoesHardware.getIpv4());
            ps.setString(6,especificacoesHardware.getIpv6());
            ps.setLong(7,especificacoesHardware.getFkMaquina());
            ps.execute();

            String update = "UPDATE maquina SET fkStatus = 1 WHERE id = ?";
            PreparedStatement ps2 = con.prepareStatement(update);
            ps2.setLong(1,especificacoesHardware.getFkMaquina());
            ps2.execute();
            ps.close();
            ps2.close();
            
            
            SendMessage teste = new SendMessage();
                   
            try {
               double memoriaTotal = (double) especificacoesHardware.getMemoriaTotal() / (1024 * 1024 * 1024);                
                teste.sendMessage(String.format("Máquina adicionada\n"+ "Memória total: %f.2", memoriaTotal));
                
            } catch (IOException ex) {
                Logger.getLogger(MaquinaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
                             
            
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
