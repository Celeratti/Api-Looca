package br.com.celeratti.dao;

import br.com.celeratti.domain.ConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class CriacaoDeTabelas {

    public void CriarTabelas(JdbcTemplate connection) {
        ConnectionFactory factory =  new ConnectionFactory();

        JdbcTemplate con = factory.getConnection();

        con.execute("DROP TABLE IF EXISTS empresa");
        con.execute("CREATE TABLE IF NOT EXISTS `empresa` (\n" +
                "  `idempresa` INT NOT NULL,\n" +
                "  `nomeFantasia` VARCHAR(150) NULL,\n" +
                "  `razaoSocial` VARCHAR(150) NULL,\n" +
                "  `email` VARCHAR(150) NULL,\n" +
                "  `telefone` CHAR(11) NULL,\n" +
                "  `cnpj` CHAR(14) NULL,\n" +
                "  `cep` CHAR(8) NULL,\n" +
                "  `numero` INT NULL,\n" +
                "  PRIMARY KEY (`idempresa`))");

        con.execute("DROP TABLE IF EXISTS funcionario");
        con.execute("CREATE TABLE IF NOT EXISTS `funcionario` (\n" +
                "  `idfuncionario` INT NOT NULL,\n" +
                "  `nome` VARCHAR(150) NULL,\n" +
                "  `sobrenome` VARCHAR(150) NULL,\n" +
                "  `telefone` CHAR(11) NULL,\n" +
                "  `email` VARCHAR(150) NULL,\n" +
                "  `senha` VARCHAR(125) NULL,\n" +
                "  `cargo` VARCHAR(45) NULL,\n" +
                "  `esta_ativo` INT NULL,\n" +
                "  `fkempresa` INT NOT NULL,\n" +
                "  PRIMARY KEY (`idfuncionario`, `fkempresa`)," +
                "FOREIGN KEY(fkempresa) REFERENCES empresa (idempresa))");

        con.execute("DROP TABLE IF EXISTS grupoComponentes");
        con.execute("CREATE TABLE IF NOT EXISTS `grupoComponentes` (\n" +
                "  `idcomponentes` INT NOT NULL auto_increment,\n" +
                "  `memoriaEmUso` DOUBLE NULL,\n" +
                "  `discoTempoDeTransferencia` DOUBLE NULL,\n" +
                "  `cpuUtilizacao` INT NULL,\n" +
                "  `dtInsercao` DATE NULL,\n" +
                "  `horaInsercao` TIME NULL,\n" +
                "  PRIMARY KEY (`idcomponentes`));");

        con.update("INSERT INTO empresa (idempresa,nomefantasia,razaosocial,email,telefone,cnpj,cep,numero) VALUES(" +
                "1,'ViaMobilidade','Viamobi LTDA','viamobilidade@gmail.com','11986023222','12345678910231','03566702',12)");
    }
}
