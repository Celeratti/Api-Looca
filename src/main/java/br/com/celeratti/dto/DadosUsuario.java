package br.com.celeratti.dto;

public record DadosUsuario(
        String email,
        String senha,
        int status,
        int fkEmpresa
) {
}
