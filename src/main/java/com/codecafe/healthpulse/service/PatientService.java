package com.codecafe.healthpulse.service;

import com.codecafe.healthpulse.dto.DashboardStats;
import com.codecafe.healthpulse.dto.PatientDTO;
import com.codecafe.healthpulse.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient findById(String id);

    Patient create(PatientDTO dto);

    Patient update(String id, PatientDTO dto);

    void deleteById(String id);

    List<Patient> findByDepartment(String department);

    List<Patient> searchByName(String name);

    DashboardStats getDashboardStats();
}
