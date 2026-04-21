package com.medical.ai.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Prescription {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(unique = true)
 private String imageHash;
 @Lob
 private String aiResponse;
 private LocalDateTime createdAt = LocalDateTime.now();
}
