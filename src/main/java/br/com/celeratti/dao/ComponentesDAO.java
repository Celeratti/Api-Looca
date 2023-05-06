package br.com.celeratti.dao;

import br.com.celeratti.model.Componentes;
import br.com.celeratti.util.Maquina;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
