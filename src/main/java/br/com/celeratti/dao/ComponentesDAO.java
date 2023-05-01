package br.com.celeratti.dao;

import br.com.celeratti.model.Componentes;
import br.com.celeratti.util.Maquina;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ComponentesDAO {

    public void inserirDadosComponentes(Maquina maquina) {
        String sql = "INSERT INTO grupoComponentes(memoriaEmUso, discoTempoDeTransferencia,cpuUtilizacao,dtInsercao, horaInsercao) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement ps;
            ps = maquina.getCon().prepareStatement(sql);
            ps.setLong(1,maquina.getComponentes().getMemoriaEmUso());
            ps.setLong(2,maquina.getComponentes().getDiscoTempoResposta());
            ps.setDouble(3, maquina.getComponentes().getCpuUtilizacao());
            ps.setDate(4,Date.valueOf(LocalDate.now()));
            ps.setTime(5,Time.valueOf(LocalTime.now()));
            ps.execute();
            ps.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
