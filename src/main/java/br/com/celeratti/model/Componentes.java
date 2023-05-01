package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.util.Conversor;

import java.text.DecimalFormat;

public class Componentes {
    private Long memoriaEmUso;
    private Long discoTempoResposta;
    private Double cpuUtilizacao;

    public Componentes(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.discoTempoResposta = looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }

    public void capturar(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.discoTempoResposta = looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }


    public Long getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Long getDiscoTempoResposta() {
        return discoTempoResposta;
    }

    public Double getCpuUtilizacao() {
        return cpuUtilizacao;
    }
    @Override
    public String toString() {
        return "Componentes:" + "\n" +
                "memoriaEmUso:" + memoriaEmUso + "\n" +
                ", discoTempoResposta:" + discoTempoResposta + "\n" +
                ", cpuUtilizacao:" + cpuUtilizacao;
    }
}
