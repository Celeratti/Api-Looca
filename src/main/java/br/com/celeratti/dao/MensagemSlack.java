package br.com.celeratti.dao;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.io.IOException;

public class MensagemSlack {  
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
    

    