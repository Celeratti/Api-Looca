
package br.com.celeratti.dto;

public class DadosSlack {
    private Integer andar;
    private String nome;
    
    
    
    public DadosSlack() {
    }
    

    public DadosSlack(Integer andar, String estacao) {
        this.andar = andar;
        this.nome = estacao;
    }
    
    

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }




    public String getEstacao() {
        System.out.println(nome);
        return nome;
    }

    public void setEstacao(String estacao) {
        this.nome = estacao;
    }
    
    
}
