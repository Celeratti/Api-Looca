package br.com.celeratti.dto;

public class DadosMaquina{
    private Long id;
    private String nomeIdentificador;
    private String status;
    private int fkEmpresa;

    public DadosMaquina() {
    }

    public DadosMaquina(Long id, String nomeIdentificador, String status, int fkEmpresa) {
        this.id = id;
        this.nomeIdentificador = nomeIdentificador;
        this.status = status;
        this.fkEmpresa = fkEmpresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeIdentificador() {
        return nomeIdentificador;
    }

    public void setNomeIdentificador(String nomeIdentificador) {
        this.nomeIdentificador = nomeIdentificador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(int fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
