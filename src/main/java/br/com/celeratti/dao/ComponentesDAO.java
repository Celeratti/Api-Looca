package br.com.celeratti.dao;

import br.com.celeratti.model.Componentes;
import br.com.celeratti.util.Maquina;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ComponentesDAO {
    private Connection con;

    public ComponentesDAO(Connection connection) {
        this.con = connection;
    }

    public void inserirDadosComponentes(Maquina maquina) {
        String sql = "INSERT INTO grupoComponentes(memoriaEmUso,memoriaDisponivel, " +
                "discoTempoDeResposta,cpuUtilizacao,dtInsercao, horaInsercao, fkmaquina) VALUES (?,?,?," +
                "?,?,?,?);";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,maquina.getComponentes().getMemoriaEmUso());
            ps.setDouble(2,maquina.getComponentes().getMemoriaDisponivel());
            ps.setDouble(3,maquina.getComponentes().getDiscoTempoResposta());
            ps.setDouble(4,maquina.getComponentes().getCpuUtilizacao());
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            ps.setTime(6, Time.valueOf(LocalTime.now()));
            ps.setInt(7, Maquina.getId());
            ps.execute();
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
