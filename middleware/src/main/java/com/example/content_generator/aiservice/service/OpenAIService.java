package com.example.content_generator.aiservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OpenAIService {

    private final WebClient webClient;

    @Value("${azure.openai.key}")
    private String azureOpenaiKey;

    @Value("${azure.openai.endpoint_url}")
    private String endpointUrl;


    public OpenAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateContent(String message, String type) {
        String requestBody = getRequestData(message, type);

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", azureOpenaiKey);
        headers.set("Content-Type", "application/json");

        // Make the API call
        Mono<ResponseEntity<String>> responseMono = webClient.post()
                .uri(endpointUrl)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(String.class);

        try {
            // Block to get the response synchronously (or handle asynchronously as needed)
            ResponseEntity<String> responseEntity = responseMono.block();

            // Return the response body
            return responseEntity != null ? responseEntity.getBody() : null;

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private static String getRequestData(String message, String type) {
        String additionalQuery = switch (type.toLowerCase()) {
            case "mail_template" ->
                    ", I need an email template in HTML format and a subject line for a promotional campaign. The template should feature a banner with the product title, a product image with a royal golden and green edge and a glowing effect (the image should be converted to base64 and included in the template), and campaign content. I only want to receive responses that directly address this request. The deals and offers should be prominently displayed, and the email content should be easy to read, with important words emphasized. Also, the email should be concise and take no longer than 5 minutes to read.";
            case "social_media" ->
                    ", I need the social media content includes campaign materials with emojis and product images featuring the main features. The promotions and offers should be prominently displayed, and the content should be easy to read with important words highlighted. The content should not take longer than 5 minutes to read";
            default ->
                    ", I need a blog or article that includes campaign details and product images showcasing the main features. The promotions and offers should be prominently highlighted, and the content should be easy to read with important words emphasized. The content should not take longer than 10 to 15 minutes to read.";
        };

        // Create the request body
        return String.format("""
                {
                  "messages": [
                    {
                      "role": "user",
                      "content": [
                        {
                          "type": "text",
                          "text": "%s"
                        }
                      ]
                    }
                  ],
                  "temperature": 0.7,
                  "top_p": 0.95,
                  "max_tokens": 800
                }""", message.concat(additionalQuery)).replaceAll("\n", "");
    }
}
