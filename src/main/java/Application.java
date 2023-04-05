import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import br.com.celeratti.services.ComponentesServices;
import com.github.britooo.looca.api.core.Looca;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class Application {
    public static void main(String[] args) {
        Looca looca = new Looca();
//        boolean inserindo = true;
        Componentes componentes = new Componentes(looca);
        ComponentesServices services = new ComponentesServices();
        for (int i = 0;i < 5; i++){
            services.enviarProBanco(componentes);
        }
    }
}
