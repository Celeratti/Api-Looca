package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class Componentes {
    private Long memoriaEmUso;
    private Double discoUso;
    private Double cpuUtilizacao;

    public Componentes(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        gravarVolumes(looca);
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }

    public void capturar(Looca looca) {
        this.memoriaEmUso = looca.getMemoria().getEmUso();
        gravarVolumes(looca);
        this.cpuUtilizacao = looca.getProcessador().getUso();
    }

    private void gravarVolumes(Looca looca) {
        List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

        Double tamanhoDisponivelVolumes = 0.0;
        Double tamanhoTotalVolumes = 0.0;
        for (int i = 0;i< volumes.size();i++){
            tamanhoDisponivelVolumes += volumes.get(i).getDisponivel().doubleValue();
            tamanhoTotalVolumes += volumes.get(i).getTotal().doubleValue();
        }
        this.discoUso =
                ((tamanhoTotalVolumes - tamanhoDisponivelVolumes) / tamanhoTotalVolumes)*100;
    }


    public Long getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public Double getDiscoUso() {
        return discoUso;
    }

    public Double getCpuUtilizacao() {
        return cpuUtilizacao;
    }
    @Override
    public String toString() {
        return "Componentes:" + "\n" +
                "memoriaEmUso:" + memoriaEmUso + "\n" +
                ", discoTempoResposta:" + discoUso + "\n" +
                ", cpuUtilizacao:" + cpuUtilizacao;
    }
}
