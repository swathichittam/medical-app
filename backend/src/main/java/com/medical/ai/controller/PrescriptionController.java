package com.medical.ai.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;

import com.medical.ai.service.PrescriptionService;
import com.medical.ai.dto.ApiResponse;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

 @Autowired private PrescriptionService service;

 @PostMapping("/upload")
 public Mono<ResponseEntity<ApiResponse<?>>> upload(@RequestParam("file") MultipartFile file){
  return service.handleUpload(file).map(ResponseEntity::ok);
 }
}
