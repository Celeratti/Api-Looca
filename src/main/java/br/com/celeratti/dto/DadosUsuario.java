package br.com.celeratti.dto;

public class DadosUsuario{
    private String email;
    private String senha;
    private int esta_ativo;
    private int fkEmpresa;
    public DadosUsuario() {
    }

    public DadosUsuario(String email, String senha, int esta_ativo, int fkEmpresa) {
        this.email = email;
        this.senha = senha;
        this.esta_ativo = esta_ativo;
        this.fkEmpresa = fkEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getEsta_ativo() {
        return esta_ativo;
    }

    public void setEsta_ativo(int esta_ativo) {
        this.esta_ativo = esta_ativo;
    }

    public int getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(int fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}
