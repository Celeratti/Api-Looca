package br.com.celeratti.dao;


import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;

import java.io.IOException;

/**
 * This example demonstrates the use of {@link HttpPost} request method.
 * @author Ramesh Fadatare
 */


/**
 *
 * @author PAULOROBERTODEALMEID
 */
public class SendMessage {
    
        
    
    
    public void sendMessageNovaMaquina(String message) throws IOException {
        
        String webHookUrl = "https://hooks.slack.com/services/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";
            String channel = "novas-maquinas";
        
        try {

            Payload payload = Payload.builder()
                    .channel(channel)
                    .username("Celeratti")
                    .text(message)
                    .build();

            WebhookResponse response = Slack.getInstance().send(webHookUrl, payload);
        }catch(Exception e) {
            System.out.printf("Erro ao enviar mensagem: %s", e.getMessage());
        }

    }
    
       public void sendMessageAlertas(String message) throws IOException {
        
        String webHookUrl = "https://hooks.slack.com/services/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";
            String channel = "novas-maquinas";
        
        try {

            Payload payload = Payload.builder()
                    .channel(channel)
                    .username("Celeratti")
                    .text(message)
                    .build();

            WebhookResponse response = Slack.getInstance().send(webHookUrl, payload);
        }catch(Exception e) {
            System.out.printf("Erro ao enviar mensagem: %s", e.getMessage());
        }

    }
    
}
    

    