package br.com.celeratti.dao;

import br.com.celeratti.util.Maquina;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ComponentesDAO {

    public void inserirDadosComponentes(Maquina maquina) throws IOException {
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
            
            File arquivo = new File("logINFO" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
     + ".txt");

        if (!arquivo.exists()) {
           arquivo.createNewFile();
        }

        List<String> lista = new ArrayList<>();
        lista.add("A máquina do usuário: " + System.getProperty("user.home") + " fez uma inserção  de dados neste momento: "
          + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        Files.write(Paths.get(arquivo.getPath()), lista, StandardOpenOption.APPEND);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
