package br.com.celeratti.model;

public class Usuario {
    private Long id;
    private String email;
    private String senha;

    public Usuario(Long id,String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
