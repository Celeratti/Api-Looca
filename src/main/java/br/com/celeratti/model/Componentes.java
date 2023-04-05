package br.com.celeratti.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import com.github.britooo.looca.api.group.servicos.ServicoGrupo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

public class Componentes {
    private final Sistema sistema;
    private final Memoria memoria;
    private final Processador processador;
    private final Temperatura temperatura;
    private final DiscoGrupo grupoDeDiscos;
    private final ServicoGrupo grupoDeServicos;
    private final ProcessoGrupo grupoDeProcessos;

    public Componentes(Looca looca) {
        sistema = looca.getSistema();
        memoria = looca.getMemoria();
        processador = looca.getProcessador();
        temperatura = looca.getTemperatura();
        grupoDeDiscos = looca.getGrupoDeDiscos();
        grupoDeServicos = looca.getGrupoDeServicos();
        grupoDeProcessos = looca.getGrupoDeProcessos();
    }

    public Sistema getSistema() {
        return sistema;
    }

    public Memoria getMemoria() {
        return memoria;
    }

    public Processador getProcessador() {
        return processador;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public DiscoGrupo getGrupoDeDiscos() {
        return grupoDeDiscos;
    }

    public ServicoGrupo getGrupoDeServicos() {
        return grupoDeServicos;
    }

    public ProcessoGrupo getGrupoDeProcessos() {
        return grupoDeProcessos;
    }
}
