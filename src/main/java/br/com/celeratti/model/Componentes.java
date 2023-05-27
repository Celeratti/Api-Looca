package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.List;

public class Componentes {
    private float memoriaEmUso;
    private float discoUso;
    private Double cpuUtilizacao;
    private double latencia;

    public Componentes(Looca looca) {
        this.memoriaEmUso = ((float) looca.getMemoria().getEmUso() / looca.getMemoria().getTotal())*100;
        gravarVolumes(looca);
        this.cpuUtilizacao = looca.getProcessador().getUso();
        latencia();
    }

    public void capturar(Looca looca) {
        this.memoriaEmUso = ((float) looca.getMemoria().getEmUso() / looca.getMemoria().getTotal())*100;
        gravarVolumes(looca);
        this.cpuUtilizacao = looca.getProcessador().getUso();
        latencia();
    }
    public void latencia() {
        try {
            String url = "https://spring-azure--demo.azurewebsites.net/home/conexao";
            long startTime = System.nanoTime();

            // Criar conexão HTTP
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Obter a resposta da requisição
            int responseCode = connection.getResponseCode();

            // Ler o corpo da resposta (opcional)
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

            long endTime = System.nanoTime();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                long latencyNs = endTime - startTime;
                this.latencia =  (double) latencyNs / 1_000_000; // Convertendo para milissegundos
            } else {
                System.out.println("Erro na requisição: " + responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
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


    public double getLatencia() {
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
        return "Componentes: " + "%\n" +
                "Uso de memória: " + memoriaEmUso + "%\n" +
                "Uso de disco: " + discoUso + "%\n" +
                "Latência: " + latencia + " ms\n" +
                "Uso de CPU: " + cpuUtilizacao;
    }
}
