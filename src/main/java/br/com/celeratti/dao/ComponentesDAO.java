package br.com.celeratti.dao;

import br.com.celeratti.util.Maquina;

import java.sql.*;
import java.time.LocalDateTime;

public class ComponentesDAO {

    public void inserirDadosComponentes(Maquina maquina) {
        String sql = "INSERT INTO grupoComponentes(memoriaEmUso, discoUso," +
                "cpuUtilizacao,dataHoraInsercao, fkMaquina) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement ps;
            ps = maquina.getCon().prepareStatement(sql);
            ps.setDouble(1,maquina.getComponentes().getMemoriaEmUso());
            ps.setDouble(2,maquina.getComponentes().getDiscoUso());
            ps.setDouble(3, maquina.getComponentes().getCpuUtilizacao());
            ps.setObject(4, LocalDateTime.now());
            ps.setLong(5,maquina.getId());
            ps.execute();
            ps.close();
            System.out.println("ID Maquina" + maquina.getId() +
                    "Memória em uso: " + maquina.getComponentes().getMemoriaEmUso() +
                    "Uso de disco: " +
                    maquina.getComponentes().getDiscoUso() +
                    "Utilização de CPU: " +
                    maquina.getComponentes().getCpuUtilizacao() +
                    "Momento captura: " +
                    Timestamp.valueOf(LocalDateTime.now()) );
            PreparedStatement ps2;
            ps2 = maquina.getConAzure().prepareStatement(sql);
            ps2.setFloat(1, maquina.getComponentes().getMemoriaEmUso());
            ps2.setFloat(2, maquina.getComponentes().getDiscoUso());
            ps2.setInt(3, maquina.getComponentes().getCpuUtilizacao().intValue());
            ps2.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps2.setInt(5,maquina.getId().intValue());
            ps2.execute();
            ps2.close();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
