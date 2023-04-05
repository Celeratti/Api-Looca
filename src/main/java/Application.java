import br.com.celeratti.model.Componentes;
import com.github.britooo.looca.api.core.Looca;

public class Application {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Componentes componentes = new Componentes(looca);
//        System.out.println(componentes.getSistema());
        System.out.println(componentes.getMemoria());

        Long memoriaDisponivel = componentes.getMemoria().getDisponivel();
        Long memoriaEmUso = componentes.getMemoria().getEmUso();
        Long memoriaTotal = componentes.getMemoria().getTotal();
        System.out.println("Memoria Disponivel: " + memoriaDisponivel + "\n" + "Memória Em Uso: " + memoriaEmUso + "\n" + "Memória Total: " + memoriaTotal);
//        System.out.println(componentes.getTemperatura());
//        System.out.println(componentes.getProcessador());
//        System.out.println(componentes.getGrupoDeDiscos());
//        System.out.println(componentes.getGrupoDeServicos());
//        System.out.println(componentes.getGrupoDeProcessos());
    }
}
