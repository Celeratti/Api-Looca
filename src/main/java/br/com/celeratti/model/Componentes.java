package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Componentes {
    private float memoriaEmUso;
    private float discoUso;
    private Double cpuUtilizacao;
    private int latencia;

    public Componentes(Looca looca) {
        this.memoriaEmUso = ((float) looca.getMemoria().getEmUso() / looca.getMemoria().getTotal())*100;
        gravarVolumes(looca);
        this.cpuUtilizacao = looca.getProcessador().getUso();
        capturarLatencia(looca);
    }

    public void capturar(Looca looca) {
        this.memoriaEmUso = ((float) looca.getMemoria().getEmUso() / looca.getMemoria().getTotal())*100;
        gravarVolumes(looca);
        this.cpuUtilizacao = looca.getProcessador().getUso();
        capturarLatencia(looca);
    }
    private void capturarLatencia(Looca looca) {
        String host = "www.google.com";
        int timeout = 5000;

        try {
            long startTime = System.currentTimeMillis();
            InetAddress address = InetAddress.getByName(host);
            boolean reachable = address.isReachable(timeout);
            long endTime = System.currentTimeMillis();

            if (reachable) {
                this.latencia = (int) (endTime - startTime);
            } else {
                System.out.println("Host inacessível.");
            }
        } catch (UnknownHostException e) {
            System.out.println("Host desconhecido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private void gravarVolumes(Looca looca) {
        List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();

        Double tamanhoDisponivelVolumes = 0.0;
        Double tamanhoTotalVolumes = 0.0;
        for (int i = 0;i< volumes.size();i++){
            tamanhoDisponivelVolumes += volumes.get(i).getDisponivel().doubleValue();
            tamanhoTotalVolumes += volumes.get(i).getTotal().doubleValue();
        }
        this.discoUso = (float)
                ((tamanhoTotalVolumes - tamanhoDisponivelVolumes) / tamanhoTotalVolumes)*100;
    }


    public int getLatencia() {
        return latencia;
    }
    public float getMemoriaEmUso() {
        return memoriaEmUso;
    }

    public float getDiscoUso() {
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
