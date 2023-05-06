package br.com.celeratti.dto;

public record DadosMaquina(
        Long id,
        String nomeIdentificador,
        int status,
        int fkEmpresa
) {
}
