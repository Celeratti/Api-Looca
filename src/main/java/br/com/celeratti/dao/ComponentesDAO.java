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
                "cpuUtilizacao,latencia,Insercao) VALUES (?,?,?,?,?);";
        try{
            PreparedStatement ps;
            ps = maquina.getCon().prepareStatement(sql);
            ps.setDouble(1,maquina.getComponentes().getMemoriaEmUso());
            ps.setDouble(2,maquina.getComponentes().getDiscoUso());
            ps.setDouble(3, maquina.getComponentes().getCpuUtilizacao());
            ps.setInt(4, maquina.getComponentes().getLatencia());
            ps.setObject(5, LocalDateTime.now());
            ps.execute();
            ps.close();
            System.out.println("\n\n| ID Maquina" + maquina.getId() +
                    " | Memória em uso: " + maquina.getComponentes().getMemoriaEmUso() +
                    " | Uso de disco: " +
                    maquina.getComponentes().getDiscoUso() +
                    " | Utilização de CPU: " +
                    maquina.getComponentes().getCpuUtilizacao() +
                    " | Latência: " + maquina.getComponentes().getLatencia() +
                    " | Momento captura: " +
                    Timestamp.valueOf(LocalDateTime.now()));
            PreparedStatement ps2;
            ps2 = maquina.getConAzure().prepareStatement("INSERT INTO grupoComponentes(memoriaEmUso, discoUso," +
                    "cpuUtilizacao,latencia,Insercao,fkMaquina) VALUES (?,?,?,?,?,?);");
            ps2.setFloat(1, maquina.getComponentes().getMemoriaEmUso());
            ps2.setFloat(2, maquina.getComponentes().getDiscoUso());
            ps2.setInt(3, maquina.getComponentes().getCpuUtilizacao().intValue());
            ps2.setInt(4, maquina.getComponentes().getLatencia());
            ps2.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps2.setInt(6,maquina.getId().intValue());
            ps2.execute();
            PreparedStatement psIdInsercao = maquina.getConAzure().prepareStatement("SELECT TOP 1 * FROM [dbo].[grupoComponentes] ORDER BY insercao DESC;");
            ResultSet rs = psIdInsercao.executeQuery();
            int idInsercao = 0;
            while (rs.next()){
                idInsercao = rs.getInt(1);
            }
            PreparedStatement psMemoria;
            if(maquina.getComponentes().getMemoriaEmUso() > 39.9 && maquina.getComponentes().getMemoriaEmUso() < 70.0){
                System.out.println("\n\nA memória está entre 40% e 69%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psMemoria = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,1,%d)",idInsercao));
                psMemoria.execute();
            }else if(maquina.getComponentes().getMemoriaEmUso() > 69.9 && maquina.getComponentes().getMemoriaEmUso() < 90){
                System.out.println("\n\nA memória está entre 70% e 89%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psMemoria = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,1,%d)",idInsercao));
                psMemoria.execute();
            }else if(maquina.getComponentes().getMemoriaEmUso() > 89.9){
                System.out.println("\n\nA memória está acima de 90%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psMemoria = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,1,%d)",idInsercao));
                psMemoria.execute();
            }
            PreparedStatement psDisco;
            if(maquina.getComponentes().getDiscoUso() > 54.9 && maquina.getComponentes().getDiscoUso() < 79.0) {
                System.out.println("\n\nO disco está entre 55% e 78%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psDisco = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,2,%d)", idInsercao));
                psDisco.execute();
            }else if(maquina.getComponentes().getDiscoUso() > 78.9 && maquina.getComponentes().getDiscoUso() < 86.0) {
                System.out.println("\n\nO disco está entre 79% e 85%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psDisco = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,2,%d)", idInsercao));
                psDisco.execute();
            }else if (maquina.getComponentes().getDiscoUso() > 85.9){
                System.out.println("\n\nO disco está acima de 85%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psDisco = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,2,%d)", idInsercao));
                psDisco.execute();
            }
            PreparedStatement psCpu;
            if(maquina.getComponentes().getCpuUtilizacao() > 64.9 && maquina.getComponentes().getCpuUtilizacao() < 80.0) {
                System.out.println("\n\nA CPU está entre 65% e 79%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psCpu = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,4,%d)", idInsercao));
                psCpu.execute();
            }else if(maquina.getComponentes().getCpuUtilizacao() > 79.9 && maquina.getComponentes().getCpuUtilizacao() < 90.0) {
                System.out.println("\n\nA CPU está entre 80% e 89%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psCpu = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,4,%d)", idInsercao));
                psCpu.execute();
            }else if(maquina.getComponentes().getCpuUtilizacao() > 89.9) {
                System.out.println("\n\nA CPU está acima de 90%...");
                System.out.println("Inserindo alerta no banco de dados...");
                psCpu = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,4,%d)", idInsercao));
                psCpu.execute();
            }
            PreparedStatement psLatencia;
            if(maquina.getComponentes().getLatencia() > 49 && maquina.getComponentes().getLatencia() < 101) {
                System.out.println("\n\nA latência está entre 50ms e 100ms...");
                System.out.println("Inserindo alerta no banco de dados...");
                psLatencia = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,3,%d)", idInsercao));
                psLatencia.execute();
            }else if(maquina.getComponentes().getLatencia() > 101 && maquina.getComponentes().getLatencia() < 200) {
                System.out.println("\n\nA latência está entre 101ms e 199ms...");
                System.out.println("Inserindo alerta no banco de dados...");
                psLatencia = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,3,%d)", idInsercao));
                psLatencia.execute();
            }else if(maquina.getComponentes().getLatencia() > 200) {
                System.out.println("\n\nA latência está acima de 200ms...");
                System.out.println("Inserindo alerta no banco de dados...");
                psLatencia = maquina.getConAzure().prepareStatement(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,3,%d)", idInsercao));
                psLatencia.execute();
            }
            ps2.close();
            psIdInsercao.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
            File arquivo = new File("logINFO" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
     + ".txt");

        if (!arquivo.exists()) {
           arquivo.createNewFile();
        }

        List<String> lista = new ArrayList<>();
        lista.add("A máquina do usuário: " + System.getProperty("user.home") + " fez uma inserção  de dados neste momento: "
          + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        Files.write(Paths.get(arquivo.getPath()), lista, StandardOpenOption.APPEND);

    }
}
