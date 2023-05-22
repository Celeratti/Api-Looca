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
    
        
    private String webHookUrl = "https://hooks.slack.com/services/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";
    private String channel = "desenvolvimento-web";
    
    public void sendMessage(String message) throws IOException {
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
    

    