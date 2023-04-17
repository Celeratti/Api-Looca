package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.Janela;

import java.util.ArrayList;
import java.util.List;

public class Componentes {
    private Long memoriaEmUso;
    private Long memoriaDisponivel;

//    private Janela janelaStatus;
    private Long discoTempoResposta;
    private DiscoGrupo discoTempoDeLeitura;
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


//    public void capturarJanela(Looca looca){
//        List<Janela> Janelas = new ArrayList<>();
//        Janelas = looca.getGrupoDeJanelas().getJanelas();
//        for (int i=0;i<Janelas.size();i++){
//            if (Janelas.get(i).getTitulo().contains("Google Chrome")){
//                janelaStatus = (Janelas.get(i));
//            }
//        }
//    }

    public Long getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Long getMemoriaDisponivel() {
        return memoriaDisponivel;
    }

//    public String getJanela() {
//        return this.janelaStatus.getTitulo();
//    }
    public Long getDiscoTempoResposta() {
        return discoTempoResposta;
    }

    public DiscoGrupo getDiscoTempoDeLeitura() {
        return discoTempoDeLeitura;
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
