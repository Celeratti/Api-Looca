package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.RedeInterface;

import java.util.List;


public class EspecificacoesHardware {
    private Long memoriaTotal;
    private Long tamanhoDisco;
    private String processador;
    private Long cpuFrequencia;
    private List ipv4;
    private List ipv6;

    public EspecificacoesHardware(Looca looca) {
        this.memoriaTotal = looca.getMemoria().getTotal();
        this.tamanhoDisco = looca.getGrupoDeDiscos().getTamanhoTotal();
        this.processador = looca.getProcessador().getNome();
        this.cpuFrequencia = looca.getProcessador().getFrequencia();
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
                this.ipv4 = interfaces.get(i).getEnderecoIpv4();
                this.ipv6 = interfaces.get(i).getEnderecoIpv6();
            }

        }
    }
    
    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

}
