package br.com.celeratti.swing;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


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

        Scanner in = new Scanner( System.in );
        str = in.nextLine();

        timer.cancel();
    }
}
