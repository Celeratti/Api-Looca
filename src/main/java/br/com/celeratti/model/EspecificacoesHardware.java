package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;


public class EspecificacoesHardware {
    private Long memoriaTotal;
    private Long tamanhoDisco;
    private String processador;

    public EspecificacoesHardware(Looca looca) {
        this.memoriaTotal = looca.getMemoria().getTotal();
        this.tamanhoDisco = looca.getGrupoDeDiscos().getTamanhoTotal();
        this.processador = looca.getProcessador().getNome();
    }

    public Long getMemoriaTotal() {
        return memoriaTotal;
    }

    public Long getTamanhoDisco() {
        return tamanhoDisco;
    }
    
    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    @Override
    public String toString() {
        return "EspecificacoesHardware:" + "\n" +
                "memoriaTotal:" + memoriaTotal + "\n" +
                "tamanhoDisco:" + tamanhoDisco + "\n" +
                "processador:" + processador + "\n";
    }
}
