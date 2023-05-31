package br.com.celeratti.dao;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.io.IOException;

public class SendMessage {  
    public void sendMessageNovaMaquina(String message) throws IOException {
        String webHookUrl = "https://hooks.slack.com/services/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";
            String channel = "novas-maquinas";
        try {
            Payload payload = Payload.builder()
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
                    .text(message)
                    .build();

            WebhookResponse response = Slack.getInstance().send(webHookUrl, payload);
        }catch(Exception e) {
            System.out.printf("Erro ao enviar mensagem: %s", e.getMessage());
        }

    }
    
}
    

    