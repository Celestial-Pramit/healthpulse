package com.codecafe.healthpulse.repository;

import com.codecafe.healthpulse.enums.ClinicalStatus;
import com.codecafe.healthpulse.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findByDepartment(String department);

    // Search box: find patients by name containing keyword
    List<Patient> findByPatientNameContainingIgnoreCase(String name);

    // Business rule support: one patient per MRN
    boolean existsByMrn(String mrn);

    // Dashboard stats
    long countByStatus(ClinicalStatus status);

    long countByStatusNot(ClinicalStatus status);
}
