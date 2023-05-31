package br.com.celeratti.dao;

import br.com.celeratti.util.Maquina;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComponentesDAO {
        public String inserirDadosComponentes(Maquina maquina) {

        SendMessage enviaMensagemSlack = new SendMessage();
            
        String sql = "INSERT INTO grupoComponentes(memoriaEmUso, discoUso," +
                "cpuUtilizacao,latencia,Insercao) VALUES (?,?,?,?,?);";
        maquina.getCon().update(sql, maquina.getComponentes().getMemoriaEmUso(), maquina.getComponentes().getDiscoUso(), maquina.getComponentes().getCpuUtilizacao(), maquina.getComponentes().getLatencia(), LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        DecimalFormat formatador = new DecimalFormat("#.##");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("| ID Maquina " + maquina.getId() +
                "| Memória em uso: " + formatador.format(maquina.getComponentes().getMemoriaEmUso()) +
                "| Uso de disco: " +
                formatador.format(maquina.getComponentes().getDiscoUso()) +
                "| Utilização de CPU: " +
                formatador.format(maquina.getComponentes().getCpuUtilizacao()) +
                "| Latência: " + formatador.format(maquina.getComponentes().getLatencia()) +
                "| Momento captura: " +
                formatter.format(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))));
        maquina.getConAzure().update("INSERT INTO grupoComponentes(memoriaEmUso, discoUso," +
                "cpuUtilizacao,latencia,Insercao,fkMaquina) VALUES (?,?,?,?,?,?);", maquina.getComponentes().getMemoriaEmUso(), maquina.getComponentes().getDiscoUso(), maquina.getComponentes().getCpuUtilizacao(), maquina.getComponentes().getLatencia(), Timestamp.valueOf(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))), maquina.getId());
        String nomeIdentificador = maquina.getConAzure().queryForObject("SELECT nomeIdentificador FROM maquina WHERE id = ?",String.class,maquina.getId());
        try {
            File arquivo = new File("logINFO" + LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + ".txt");

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            List<String> lista = new ArrayList<>();
            lista.add("A máquina "+  nomeIdentificador +" fez uma inserção  de dados neste momento: "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            Files.write(Paths.get(arquivo.getPath()), lista, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }

        int idInsercao = maquina.getConAzure().queryForObject("SELECT TOP 1 id FROM [dbo].[grupoComponentes] ORDER BY insercao DESC;", Integer.class);
        if (maquina.getComponentes().getMemoriaEmUso() > 39.9 && maquina.getComponentes().getMemoriaEmUso() < 70.0) {
            System.out.println("\n\nA memória está entre 40% e 69%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,1,%d)", idInsercao));
        } else if (maquina.getComponentes().getMemoriaEmUso() > 69.9 && maquina.getComponentes().getMemoriaEmUso() < 90) {
            System.out.println("\n\nA memória está entre 70% e 89%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,1,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getMemoriaEmUso() > 89.9) {
            System.out.println("\n\nA memória está acima de 90%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,1,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        PreparedStatement psDisco;
        if (maquina.getComponentes().getDiscoUso() > 54.9 && maquina.getComponentes().getDiscoUso() < 79.0) {
            System.out.println("\n\nO disco está entre 55% e 78%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,2,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getDiscoUso() > 78.9 && maquina.getComponentes().getDiscoUso() < 86.0) {
            System.out.println("\n\nO disco está entre 79% e 85%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,2,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getDiscoUso() > 85.9) {
            System.out.println("\n\nO disco está acima de 85%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,2,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        PreparedStatement psCpu;
        if (maquina.getComponentes().getCpuUtilizacao() > 64.9 && maquina.getComponentes().getCpuUtilizacao() < 80.0) {
            System.out.println("\n\nA CPU está entre 65% e 79%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,4,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getCpuUtilizacao() > 79.9 && maquina.getComponentes().getCpuUtilizacao() < 90.0) {
            System.out.println("\n\nA CPU está entre 80% e 89%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,4,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getCpuUtilizacao() > 89.9) {
            System.out.println("\n\nA CPU está acima de 90%...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,4,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        PreparedStatement psLatencia;
        if (maquina.getComponentes().getLatencia() > 49 && maquina.getComponentes().getLatencia() < 101) {
            System.out.println("\n\nA latência está entre 50ms e 100ms...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (1,3,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getLatencia() > 101 && maquina.getComponentes().getLatencia() < 200) {
            System.out.println("\n\nA latência está entre 101ms e 199ms...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (2,3,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (maquina.getComponentes().getLatencia() > 200) {
            System.out.println("\n\nA latência está acima de 200ms...");
            System.out.println("Inserindo alerta no banco de dados...");
            maquina.getConAzure().update(String.format("INSERT INTO alertas (fkTipoAlerta,fkComponenteCausa,fkGrupoComponentes) VALUES (3,3,%d)", idInsercao));
            try {
                enviaMensagemSlack.sendMessageAlertas(String.format("Nome: %s", nomeIdentificador));
            } catch (IOException ex) {
                Logger.getLogger(ComponentesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return nomeIdentificador;
    }
}
