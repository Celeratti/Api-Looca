package br.com.celeratti.swing;

import br.com.celeratti.domain.ConnectionFactory;
import br.com.celeratti.model.Componentes;
import br.com.celeratti.services.ComponentesServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;

import com.github.britooo.looca.api.core.Looca;

public class Temporizador {
        private String str = "";

        TimerTask task = new TimerTask() {
            public void run()
            {
                if( str.equals("")) {
                    try {
                        new TelaLogin().inserir();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }else{
                    System.exit(0);
                }
            }
        };

    public void getInput() throws Exception {
        Timer timer = new Timer();
        timer.schedule( task, 60*1000 );

        BufferedReader in = new BufferedReader(
                new InputStreamReader( System.in ) );
        str = in.readLine();

        timer.cancel();
    }
}
