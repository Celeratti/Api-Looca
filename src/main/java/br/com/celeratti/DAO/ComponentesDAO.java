package br.com.celeratti.DAO;

import br.com.celeratti.model.Componentes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ComponentesDAO {
    private Connection con;

    public ComponentesDAO(Connection connection) {
        this.con = connection;
    }

    public void inserir(Componentes componentes) {
        String sql = "INSERT INTO grupoComponentes(memoriaEmUso,memoriaDisponivel, " +
                "discoTempoDeResposta,cpuUtilizacao,dtInsercao, horaInsercao) VALUES (?,?,?," +
                "?,?,?);";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,componentes.getMemoriaEmUso());
            ps.setDouble(2,componentes.getMemoriaDisponivel());
//            ps.setString(3,componentes.getJanela());
            ps.setDouble(3,componentes.getDiscoTempoResposta());
            ps.setDouble(4,componentes.getCpuUtilizacao());
            ps.setDate(5, Date.valueOf(LocalDate.now()));
            ps.setTime(6, Time.valueOf(LocalTime.now()));
            ps.execute();
            ps.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
