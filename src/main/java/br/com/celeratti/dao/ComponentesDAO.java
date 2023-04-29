package br.com.celeratti.dao;

import br.com.celeratti.model.Componentes;
import br.com.celeratti.util.Maquina;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ComponentesDAO {
    private JdbcTemplate con;

    public ComponentesDAO(JdbcTemplate connection) {
        this.con = connection;
    }

    public void inserirDadosComponentes(Maquina maquina) {
        maquina.getCon().update("INSERT INTO grupoComponentes(memoriaEmUso, " +
                "discoTempoDeTransferencia,cpuUtilizacao,dtInsercao, horaInsercao) VALUES (?,?,?," +
                "?,?);",maquina.getComponentes().getMemoriaEmUso(),
                maquina.getComponentes().getDiscoTempoResposta(),
                maquina.getComponentes().getCpuUtilizacao(),
                LocalDate.now(),
                LocalTime.now());
    }
}
