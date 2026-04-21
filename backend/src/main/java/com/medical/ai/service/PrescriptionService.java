package com.medical.ai.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import java.util.Optional;

import com.medical.ai.repository.PrescriptionRepository;
import com.medical.ai.model.Prescription;
import com.medical.ai.dto.ApiResponse;
import com.medical.ai.util.HashUtil;
import com.medical.ai.exception.AiProcessingException;

@Service
public class PrescriptionService {
 @Autowired private PrescriptionRepository repo;
 @Autowired private GeminiService geminiService;

 public Mono<ApiResponse<?>> handleUpload(MultipartFile file){
  String hash = HashUtil.generateHash(file);
  Optional<Prescription> existing = repo.findByImageHash(hash);

  if(existing.isPresent()){
   return Mono.just(new ApiResponse<>(true,"From DB",existing.get().getAiResponse()));
  }

  return geminiService.processImage(file)
    .map(res->{
     Prescription p=new Prescription();
     p.setImageHash(hash);
     p.setAiResponse(res);
     repo.save(p);
     return new ApiResponse<>(true,"Success",res);
    })
    .onErrorResume(AiProcessingException.class,
      e->Mono.just(new ApiResponse<>(false,"Image unclear. Retake.",null)))
    .onErrorResume(Exception.class,
      e->Mono.just(new ApiResponse<>(false,"Try again",null)));
 }
}
