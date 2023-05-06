package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;


public class EspecificacoesHardware {
    private Long memoriaTotal;
    private Long tamanhoDisco;
    private String processador;
    private Long cpuFrequencia;
    private String ipv4;
    private String ipv6;
    private Long fkMaquina;

    public EspecificacoesHardware(Looca looca,Long fkMaquina) {
        this.memoriaTotal = looca.getMemoria().getTotal();
        this.tamanhoDisco = looca.getGrupoDeDiscos().getTamanhoTotal();
        this.processador = looca.getProcessador().getNome();
        this.cpuFrequencia = looca.getProcessador().getFrequencia();
        this.fkMaquina = fkMaquina;
        gravarRede(looca);

    }

    public Long getMemoriaTotal() {
        return memoriaTotal;
    }

    public Long getTamanhoDisco() {
        return tamanhoDisco;
    }
    public void gravarRede(Looca looca) {
        List<RedeInterface> interfaces = looca.getRede().getGrupoDeInterfaces().getInterfaces();
        for (int i=0;i< interfaces.size();i++) {
            if (interfaces.get(i).getBytesEnviados() > 0){
                this.ipv4 = interfaces.get(i).getEnderecoIpv4().get(0);
                this.ipv6 = interfaces.get(i).getEnderecoIpv6().get(0);
            }

        }
    }

    public Long getCpuFrequencia() {
        return cpuFrequencia;
    }

    public String getIpv4() {
        return ipv4;
    }

    public String getIpv6() {
        return ipv6;
    }

    public Long getFkMaquina() {
        return fkMaquina;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

}
