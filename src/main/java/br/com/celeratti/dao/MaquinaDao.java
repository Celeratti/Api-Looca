package br.com.celeratti.dao;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.dto.DadosSlack;
import br.com.celeratti.model.EspecificacoesHardware;
import com.github.britooo.looca.api.util.Conversor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MaquinaDao {

    private final JdbcTemplate con;

    public MaquinaDao() {
        this.con = new ConnectionFactory().getConnectionAzure();
    }


    public DadosMaquina buscarMaquina(String identificacao) {
        List<DadosMaquina> dados = null;
        String sql = "SELECT m.id,m.nomeIdentificador,s.status,m.fkempresa from maquina as m JOIN status as s on m.fkStatus = s.id WHERE nomeIdentificador = ?;";
        dados = con.query(sql, new BeanPropertyRowMapper<>(DadosMaquina.class),identificacao);
        return dados.get(0);
    }
    
        public DadosSlack buscarMaquinaSlack(String identificacao) {
        List<DadosSlack> dados = null;
        String sql = "SELECT e.nome, m.andar FROM maquina m INNER JOIN estacao e ON m.fkestacao = e.id INNER JOIN linha l ON e.fkLinha = l.id WHERE m.nomeIdentificador = ?;";
        dados = con.query(sql, new BeanPropertyRowMapper<>(DadosSlack.class),identificacao);
        return dados.get(0);
    }
    

    public void enviarEspecsProBanco(EspecificacoesHardware especificacoesHardware) {
        String sql = "INSERT INTO especificacoesHardware(memoriaTotal,tamanhoDisco,processador,cpuTotal,ipv4,ipv6,fkMaquina) VALUES (?,?,?,?,?,?,?)";
        System.out.println("| Ativando nova máquina |");
        System.out.println("Especificações da máquina: ");
        System.out.println("Memória total: " + Conversor.formatarBytes(especificacoesHardware.getMemoriaTotal()));
        System.out.println("Tamanho do disco: " + Conversor.formatarBytes(especificacoesHardware.getTamanhoDisco()));
        System.out.println("Processador: " + especificacoesHardware.getProcessador());
        System.out.println("Frequência do processador: " + (especificacoesHardware.getCpuFrequencia()/1_000_000_000)+"GHz");
        System.out.println("IPV4: " + especificacoesHardware.getIpv4());
        System.out.println("IPV6: " + especificacoesHardware.getIpv6());
        con.update(sql,especificacoesHardware.getMemoriaTotal().toString(),especificacoesHardware.getTamanhoDisco(),especificacoesHardware.getProcessador(),especificacoesHardware.getCpuFrequencia(),especificacoesHardware.getIpv4(),especificacoesHardware.getIpv6(),especificacoesHardware.getFkMaquina());
        String update = "UPDATE maquina SET fkStatus = 1 WHERE id = ?";
        con.update(update,especificacoesHardware.getFkMaquina());
    }
}