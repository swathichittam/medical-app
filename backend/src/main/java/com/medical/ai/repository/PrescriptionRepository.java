package com.medical.ai.repository;
import com.medical.ai.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
 Optional<Prescription> findByImageHash(String imageHash);
}
