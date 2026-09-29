package com.codecafe.healthpulse.service.impl;

import com.codecafe.healthpulse.dto.DashboardStats;
import com.codecafe.healthpulse.enums.ClinicalStatus;
import com.codecafe.healthpulse.model.Patient;
import com.codecafe.healthpulse.repository.PatientRepository;
import com.codecafe.healthpulse.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    @Cacheable("patients")
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    @Cacheable(value = "patient", key = "#id")
    public Patient findById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    // Cache eviction: any write clears the caches so new data shows immediately
    @Override
    @CacheEvict(value = {"patients", "patient", "dashboard"}, allEntries = true)
    public Patient create(Patient patient) {
        if (patientRepository.existsByMrn(patient.getMrn())) {
            throw new IllegalArgumentException("MRN already exists");
        }
        if (patient.getStatus() == null) {
            patient = patient.toBuilder().status(ClinicalStatus.TRIAGE).build();
        }
        return patientRepository.save(patient);
    }

    @Override
    @CacheEvict(value = {"patients", "patient", "dashboard"}, allEntries = true)
    public Patient update(String id, Patient patient) {
        Patient existingPatient = patientRepository.findById(id).orElse(null);
        if (existingPatient == null) {
            return null;  // patient not found
        }
        existingPatient = existingPatient.toBuilder()
                .mrn(patient.getMrn())
                .patientName(patient.getPatientName())
                .department(patient.getDepartment())
                .assignedBed(patient.getAssignedBed())
                .acuity(patient.getAcuity())
                .status(patient.getStatus())
                .build();
        return patientRepository.save(existingPatient);
    }

    @Override
    @CacheEvict(value = {"patients", "patient", "dashboard"}, allEntries = true)
    public void deleteById(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> findByDepartment(String department) {
        return patientRepository.findByDepartment(department);
    }

    @Override
    public List<Patient> searchByName(String name) {
        return patientRepository.findByPatientNameContainingIgnoreCase(name);
    }

    @Override
    @Cacheable("dashboard")
    public DashboardStats getDashboardStats() {
        long activeInPatients = patientRepository.countByStatusNot(ClinicalStatus.DISCHARGED);
        long icuPatients = patientRepository.countByStatus(ClinicalStatus.ICU);
        long operatingTheaterPatients = patientRepository.countByStatus(ClinicalStatus.IN_SURGERY);
        long dischargedPatients = patientRepository.countByStatus(ClinicalStatus.DISCHARGED);
        return DashboardStats.builder()
                .activeInPatients(activeInPatients)
                .icuPatients(icuPatients)
                .operatingTheaterPatients(operatingTheaterPatients)
                .dischargedPatients(dischargedPatients)
                .build();
    }
}
