package br.com.celeratti.dao;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.dto.DadosMaquina;
import br.com.celeratti.model.EspecificacoesHardware;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MaquinaDao {

    private final JdbcTemplate con;

    public MaquinaDao() {
        this.con = new ConnectionFactory().getConnectionAzure();
    }

//    public MaquinaDao() {
//        this.con = new ConnectionFactory().getConnection();
//    }

    public DadosMaquina buscarMaquina(String identificacao) {
        List<DadosMaquina> dados = null;
        String sql = "SELECT m.id,m.nomeIdentificador,s.status,m.fkempresa from maquina as m JOIN status as s on m.fkStatus = s.id WHERE nomeIdentificador = ?;";
        dados = con.query(sql, new BeanPropertyRowMapper<>(DadosMaquina.class),identificacao);
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1,identificacao);
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                Long id = rs.getLong(1);
//                String nomeIdentificador = rs.getString(2);
//                int status = rs.getInt(3);
//                int fkEmpresa = rs.getInt(4);
//                dados = new DadosMaquina(id,nomeIdentificador,status,fkEmpresa);
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
        return dados.get(0);
    }

    public void enviarEspecsProBanco(EspecificacoesHardware especificacoesHardware) {
        String sql = "INSERT INTO especificacoesHardware(memoriaTotal,tamanhoDisco,processador,cpuTotal,ipv4,ipv6,fkMaquina) VALUES (?,?,?,?,?,?,?)";

        con.update(sql,especificacoesHardware.getMemoriaTotal().toString(),especificacoesHardware.getTamanhoDisco(),especificacoesHardware.getProcessador(),especificacoesHardware.getCpuFrequencia(),especificacoesHardware.getIpv4(),especificacoesHardware.getIpv6(),especificacoesHardware.getFkMaquina());
        String update = "UPDATE maquina SET fkStatus = 1 WHERE id = ?";
        con.update(update,especificacoesHardware.getFkMaquina());
//            PreparedStatement ps2 = con.prepareStatement(update);
//            ps2.setLong(1,especificacoesHardware.getFkMaquina());
//            ps2.execute();
//            ps.close();
//            ps2.close();
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
    }
}