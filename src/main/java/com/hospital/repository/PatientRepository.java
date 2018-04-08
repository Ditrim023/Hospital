package com.hospital.repository;

import com.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nikita Krutoguz
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByName(String name);
}
