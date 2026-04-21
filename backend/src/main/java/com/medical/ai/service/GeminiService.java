package com.medical.ai.service;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Base64;
import com.medical.ai.exception.AiProcessingException;

@Service
public class GeminiService {
 @Value("${gemini.api-key}") private String apiKey;
 @Autowired private WebClient webClient;

 public Mono<String> processImage(MultipartFile file){
  try{
   String base64 = Base64.getEncoder().encodeToString(file.getBytes());
   String body = "{\"contents\":[{\"parts\":[{\"text\":\"Suggest exercises\"},{\"inline_data\":{\"mime_type\":\"image/png\",\"data\":\""+base64+"\"}}]}]}";
   return webClient.post()
     .uri("https://generativelanguage.googleapis.com/v1/models/gemini-pro-vision:generateContent?key="+apiKey)
     .bodyValue(body)
     .retrieve()
     .bodyToMono(String.class)
     .onErrorResume(e->Mono.error(new AiProcessingException("AI failed")));
  }catch(Exception e){
   return Mono.error(new AiProcessingException("Invalid image"));
  }
 }
}
