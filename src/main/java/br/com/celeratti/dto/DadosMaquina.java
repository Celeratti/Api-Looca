package br.com.celeratti.dto;

public record DadosMaquina(
        Long id,
        String nomeIdentificador,
        String status,
        int fkEmpresa
) {
}
