package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;

public class Componentes {
    private Long memoriaEmUso;
    private Long memoriaDisponivel;
    private Long discoTempoResposta;
    private Double cpuUtilizacao;

    public Componentes(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.memoriaDisponivel = looca.getMemoria().getDisponivel();
        this.discoTempoResposta = looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }

    public void capturar(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.memoriaDisponivel = looca.getMemoria().getDisponivel();
        this.discoTempoResposta = looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }


    public Long getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Long getMemoriaDisponivel() {
        return memoriaDisponivel;
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
                ", memoriaDisponivel:" + memoriaDisponivel + "\n" +
                ", discoTempoResposta:" + discoTempoResposta + "\n" +
                ", cpuUtilizacao:" + cpuUtilizacao;
    }
}
