import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import br.com.celeratti.services.ComponentesServices;
import com.github.britooo.looca.api.core.Looca;
import java.sql.Connection;


public class Application {
    public static void main(String[] args) {
        Looca looca = new Looca();
        Componentes componentes = new Componentes(looca);
        ComponentesServices services = new ComponentesServices();
        ConnectionFactory connection = new ConnectionFactory();
        Connection con = connection.recuperarConexao();
            try(con){
                while (!con.isClosed()) {
                    componentes.capturar(looca);
                    services.enviarProBanco(componentes, con);
                    Thread.sleep(59000);
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }

    }
}
