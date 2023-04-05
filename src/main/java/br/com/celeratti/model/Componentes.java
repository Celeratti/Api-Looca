package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.processador.Processador;

public class Componentes {
    private Long memoriaEmUso;
    private Long memoriaDisponivel;

    private Boolean janelaStatus;
    private Long discoTempoResposta;
    private DiscoGrupo discoTempoDeLeitura;
    private Double cpuUtilizacao;

    public Componentes(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.memoriaDisponivel = looca.getMemoria().getDisponivel();
        this.janelaStatus = !looca.getGrupoDeJanelas().getJanelas().isEmpty();
        this.discoTempoResposta = looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }

    public void capturar(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        this.memoriaDisponivel = looca.getMemoria().getDisponivel();
        this.janelaStatus = !looca.getGrupoDeJanelas().getJanelas().isEmpty();
        this.discoTempoResposta = looca.getGrupoDeDiscos().getDiscos().get(0).getTempoDeTransferencia();
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }

    @Override
    public String toString() {
        return "Componentes:" + "\n" +
                "memoriaEmUso:" + memoriaEmUso + "\n" +
                ", memoriaDisponivel:" + memoriaDisponivel + "\n" +
                ", janelaStatus:" + janelaStatus + "\n" +
                ", discoTempoResposta:" + discoTempoResposta + "\n" +
                ", cpuUtilizacao:" + cpuUtilizacao;
    }

    public Long getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Long getMemoriaDisponivel() {
        return memoriaDisponivel;
    }

    public Boolean getJanelaStatus() {
        return janelaStatus;
    }

    public Long getDiscoTempoResposta() {
        return discoTempoResposta;
    }

    public DiscoGrupo getDiscoTempoDeLeitura() {
        return discoTempoDeLeitura;
    }

    public Double getCpuUtilizacao() {
        return cpuUtilizacao;
    }
}
