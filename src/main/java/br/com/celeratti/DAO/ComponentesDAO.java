package br.com.celeratti.DAO;

import br.com.celeratti.model.Componentes;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.LocalDateTime.now;

public class ComponentesDAO {
    private Connection con;

    public ComponentesDAO(Connection connection) {
        this.con = connection;
    }

    public void inserir(Componentes componentes) {
        String sql = "INSERT INTO grupoComponentes(memoriaEmUso,memoriaDisponivel, " +
                "Janelastatus,discoTempoDeResposta,cpuUtilizacao,dtInsercao, horaInsercao) VALUES (?,?,?," +
                "?,?,?,?);";
        try{
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1,componentes.getMemoriaEmUso());
            ps.setDouble(2,componentes.getMemoriaDisponivel());
            ps.setBoolean(3, componentes.getJanelaStatus());
            ps.setDouble(4,componentes.getDiscoTempoResposta());
            ps.setDouble(5,componentes.getCpuUtilizacao());
            ps.setDate(6, Date.valueOf(LocalDate.now()));
            ps.setTime(7, Time.valueOf(LocalTime.now()));

            ps.execute();
            ps.close();
            con.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

    }
}
