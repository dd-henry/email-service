package com.henry.dev.email_service.integration.ai;

import com.henry.dev.email_service.domain.GenAiResponse;
import com.henry.dev.email_service.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class HuggingFaceClient {

    private final RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiToken;

    @Value("${api.model}")
    private String model; // Ex: "openai/gpt-oss-120b:fireworks-ai"

    @Value("${api.base-url}")
    private String baseUrl; // Ex: "https://router.huggingface.co/v1/chat/completions"

    public String generateText(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        String basePrompt = """
                        Você é a Donna Paulsen de Suits, aja como ela, a partir da mensagem que eu te enviar,
                        gere uma mensagem de lembrete para mim com tom amigavel e descontraído.
                        Instruções para a geração da mensagem:
                        - Use uma linguagem amigável e profissional.
                        - Inclua um assunto relevante.
                        - Termine com uma chamada para ação clara.
                        - Seja criativo.
                        - Use emojis de forma moderada para adicionar um toque pessoal.
                        - Revise a mensagem para garantir que esteja livre de erros gramaticais e ortográficos
                        - Caso não tenha alguma informação não coloque ela vazia no email, apenas omita.
                        - A mensagem tem que lembrar a Donna Paulsen de Suits.
                        - Coloque uma da serie na mensagem.
                        - A mensagem deve lembrar o destinatário sobre o conteudo do prompt.
                        
                        prompt: 
                        """;

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(Map.of("role", "user", "content", basePrompt + prompt))
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<GenAiResponse> response = restTemplate.exchange(
                    baseUrl, HttpMethod.POST, entity, GenAiResponse.class);

            return Optional.ofNullable(response.getBody())
                    .flatMap(this::extractContent)
                    .orElseThrow(() -> new IllegalStateException("AI response processing error: null response"));
        } catch (Exception ex) {
            log.error("Error calling HuggingFace API", ex);
            throw new RuntimeException("Failed to generate text from AI", ex);
        }
    }

    private Optional<String> extractContent(GenAiResponse responseBody) {
        return Optional.ofNullable(responseBody.getChoices())
                .filter(choices -> !choices.isEmpty())
                .map(choices -> choices.getFirst().getMessage())
                .map(Message::getContent);
    }
}
